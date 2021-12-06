package UserInterface;

import UserInterface.CellFactory.NumberColumnFactory;
import UserInterface.CellFactory.OperationCellFactory;
import MainMathOperation.RPNSolver;
import UserDefinedOperation.*;
import UserInterface.CellFactory.NumberListFactory;
import VariablesManager.VariablesStorage;
import java.util.*;
import java.util.stream.Collectors;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;

/**
 * Implementation of the Calculator User Interface Controller
 *
 * @author Speranza
 * @author ermancusi
 */
public class CalculatorController {

    //FXML components
    @FXML
    private Pane titlePane, calculatorPane, operationsPane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextArea textAreaCalculator;
    @FXML
    private TextField inputNumber, inputName;
    @FXML
    private Button btnClearEntry, btnPush, btnSave, btnRestore, btnFinalCreate, btnDelete;
    @FXML
    private Text textWarning, textWarningSoft;
    @FXML
    private TableView<String> tableVariables;
    @FXML
    private TableColumn<String, String> columnVariable;
    @FXML
    private TableColumn<String, Complex> columnValue;
    @FXML
    private ListView<SupportedOperation> operationsList;
    @FXML
    private ListView<UserDefinedOperation> userDefinedList, definedOperationsList;
    @FXML
    private ListView<Operation> finalList;
    @FXML
    private ListView<Complex> stackList;

    //definition of utils variables
    private double xAxis, yAxis;
    private InputValidation check;
    private RPNSolver rpn;
    private VariablesStorage variableStorage;
    private ObservableList<UserDefinedOperation> UserDefinedOperations;
    private ObservableList<Operation> finalObservable;
    private ObservableList<SupportedOperation> operationsObservable;
    //Hash Table with operation to call
    private HashMap<String, Operation> supportedOperation;

    /**
     * Initializes the User Interface. It's executed as soon as the program
     * starts.
     *
     * @return
     */
    public void init(Stage stage) {
        Scene scene = stage.getScene();

        check = new InputValidation();
        rpn = RPNSolver.getInstance();

        //create observable lists and set them to the respective lists (components).
        finalObservable = FXCollections.observableArrayList();
        finalList.setItems(finalObservable);

        operationsObservable = FXCollections.observableArrayList();
        operationsList.setItems(operationsObservable);
        operationsList.setCellFactory(new OperationCellFactory());

        UserDefinedOperations = FXCollections.observableArrayList();
        userDefinedList.setItems(UserDefinedOperations);
        definedOperationsList.setItems(UserDefinedOperations);

        // Set list cell for complex number visualization
        stackList.setCellFactory(new NumberListFactory());
        rpn.setList(stackList);

        //Initialize the support operation HashMap
        this.supportedOperation = new HashMap();

        //set table cell columns for variables visualization
        variableStorage = new VariablesStorage();
        columnValue.setCellFactory(new NumberColumnFactory());
        variableStorage.setObserver(tableVariables, columnVariable, columnValue);

        //populate the list of supported operations
        populate();

        //disable buttons that will be developed in the next Sprint
        btnSave.setDisable(true);
        btnRestore.setDisable(true);

        // Set bindings for warning
        BooleanBinding oneElements = Bindings.size(stackList.getItems()).
                isEqualTo(1).and(textAreaCalculator.textProperty().isEqualTo("swap").
                or(textAreaCalculator.textProperty().isEqualTo("over")));

        BooleanBinding twoElements = Bindings.size(stackList.getItems()).
                lessThan(2).and(textAreaCalculator.textProperty().isEqualTo("+").
                or(textAreaCalculator.textProperty().isEqualTo("-").
                        or(textAreaCalculator.textProperty().isEqualTo("/").
                                or(textAreaCalculator.textProperty().isEqualTo("*")))));
        BooleanBinding emptyList = Bindings.size(stackList.getItems()).
                isEqualTo(0).and(textAreaCalculator.textProperty().isEqualTo("+-").
                or(textAreaCalculator.textProperty().isEqualTo("sqrt")));

        textWarning.visibleProperty().bind(oneElements.or(twoElements).or(emptyList));
        textWarningSoft.visibleProperty().bind(Bindings.size(stackList.getItems()).
                greaterThan(0).and(textAreaCalculator.textProperty().isEqualTo("clear")));

        //btnPush is disabled when the Text Area is empty
        btnPush.disableProperty().bind(Bindings.createBooleanBinding(()
                -> textAreaCalculator.getText().trim().isEmpty(),
                textAreaCalculator.textProperty()));

        //btnFinalCreate is disable if the list finalObservable is empty or inputName is empty or inputNumber is empty
        btnFinalCreate.disableProperty().bind((Bindings.size(finalObservable).isEqualTo(0)).or(inputName.textProperty().isEmpty()).or(inputNumber.textProperty().isEmpty()));

        //btnDelete is disable if finalObservable does not contain elements.
        btnDelete.disableProperty().bind((Bindings.size(finalObservable).isEqualTo(0)));

        //when the user presses the "back space" button on physical keyboard
        //the last element in the specific text area is deleted.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER && textAreaCalculator.getText().length() > 0) {
                push(new ActionEvent());

            }
            if (e.getCode() == KeyCode.BACK_SPACE && textAreaCalculator.getText().length() > 0) {
                textAreaCalculator.setText(textAreaCalculator.getText().substring(0, textAreaCalculator.getText().length() - 1));
                textAreaCalculator.end();
            }
            if (e.getCode() == KeyCode.BACK_SPACE && inputName.getText().length() > 0 && inputName.isFocused()) {
                inputName.setText(inputName.getText().substring(0, inputName.getText().length() - 1));
                inputName.end();
            }
            if (e.getCode() == KeyCode.BACK_SPACE && inputNumber.getText().length() > 0 && inputNumber.isFocused()) {
                inputNumber.setText(inputNumber.getText().substring(0, inputNumber.getText().length() - 1));
                inputNumber.end();
            }

            e.consume();
        });

        //if the user presses the "back space" button on physical keyboard
        //for more than 0.2 seconds the entire Text Area is cleaned up.
        btnClearEntry.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            long startTime;

            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    startTime = System.currentTimeMillis();
                } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    if (System.currentTimeMillis() - startTime > 0.2 * 1000) {
                        textAreaCalculator.setText("");
                    }
                }
            }
        });

        //close the Calculator
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        //minimize the Calculator
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        //allows the user to move around, on the screen, the calculator
        titlePane.setOnMousePressed(mouseEvent -> {
            xAxis = mouseEvent.getSceneX();
            yAxis = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xAxis);
            stage.setY(mouseEvent.getScreenY() - yAxis);
        });

    }

    /**
     * Gets the number associated with the on-screen keyboard button and shows
     * it in the User Interface Text Area.
     *
     * @return
     */
    @FXML
    private void onNumberPress(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();
        textAreaCalculator.setText(textAreaCalculator.getText() + number);
    }

    /**
     * Gets the operation associated with the on-screen keyboard button and
     * shows it in the User Interface Text Area.
     *
     * @return
     */
    @FXML
    private void onOperationPress(ActionEvent event) {
        String operation = ((Button) event.getSource()).getText();
        textAreaCalculator.setText(textAreaCalculator.getText() + operation);
    }

    /**
     * When the "Clear entry" (⌫) button is pressed, the last item of the Text
     * Area is cleaned up.
     *
     * @return
     */
    @FXML
    private void deleteLast(ActionEvent event) {
        if (textAreaCalculator.getText().length() > 0) {
            textAreaCalculator.setText(textAreaCalculator.getText().substring(0, textAreaCalculator.getText().length() - 1));
        }
    }

    /**
     * When the "push" (↑) button is pressed, the value in the Text Area is
     * pushed in the stack if it's a number, otherwise is executed the operation
     * on the variable indicated by the user and entered it in the associated
     * table. The function checks if the input is in a right format and checks
     * if the user enters the operations supported by the Calculator.
     *
     * @return
     */
    @FXML
    private void push(ActionEvent event) {
        //define of used variables
        String input = textAreaCalculator.getText();
        String operation = check.checkOperation(input);
        String supportedVariable = check.checkVariable(input);

        textAreaCalculator.clear();

        Operation toExecute = null;
        Complex number = check.parseComplex(input, "j");
        try {
            //Input is recognized as Complex number, then perform a push onto the stack
            if (number != null) {
                ((StackOperation) supportedOperation.get("push")).setNumber(check.parseComplex(input, "j"));
                toExecute = supportedOperation.get("push");
            } else if (operation != null) {
                //Input is recognized as an Arithmetical or Stack operation, then select it
                toExecute = supportedOperation.get(operation);
            } else if (supportedVariable != null) {
                // Input is recognized as variable operation, then set the variable and execute it
                String varOperation = supportedVariable.substring(0, 1);
                String variable = supportedVariable.substring(1);
                ((VariableOperation) this.supportedOperation.get(varOperation + "var")).setVariableName(variable);
                toExecute = this.supportedOperation.get(varOperation + "var");
            }
            //Execute the operation
            toExecute.execute();
            //Scroll stack view
            stackList.scrollTo(stackList.getItems().size());
        } catch (NoSuchElementException | ArithmeticException e) {
            createAlert(AlertType.ERROR, "Error", "Look, an Error!",
                    "\nImpossible to continue.\n" + e.getMessage());
        } catch (NullPointerException ex) {
            createAlert(AlertType.ERROR, "Error", "Look, an Error!",
                    "\nInvalid input inserted:\n" + input);
        }
    }

    /**
     * Allows the User to view the window to define a custom operation.
     *
     * @return
     */
    @FXML
    private void onCreatePress(ActionEvent event) {
        operationsPane.setVisible(true);
        operationsPane.setDisable(false);
        calculatorPane.setVisible(false);
        calculatorPane.setDisable(true);
    }

    /**
     * Allows the User to create a custom operation after its definition.
     *
     * @return
     */
    @FXML
    private void onFinalCreatePress(ActionEvent event) {
        String name = inputName.getText();
        String operandsNumber = inputNumber.getText();

        if (name.contains("$") || name.contains("£") || name.contains("#") || name.contains("!") || name.contains("?") || name.contains("%") || name.contains("&")) {
            createAlert(AlertType.ERROR, "Error", "Look, an Error!", "The operation name can’t contain special characters ($, £,#,!,?,%,&)");
            return;
        }

        try {
            Integer.parseInt(operandsNumber);
        } catch (Exception e) {
            createAlert(AlertType.ERROR, "Error", "Look, an Error!", "The operands number must be an integer!");
            return;
        }

        // if (name.contains("$")) {
        // createAlert(AlertType.ERROR, "Error", "Look, an Error!", "The operands number must be an integer!");
        //return;
        // }
        int operatorsNumber = Integer.parseInt(inputNumber.getText());
        UserDefinedOperation u = new UserDefinedOperation(name, operatorsNumber, finalObservable.stream().collect(Collectors.toList()));

        if (UserDefinedOperations.contains(u)) {
            createAlert(AlertType.ERROR, "Error", "Look, an Error!", "An operation with this name already exists, please change it.");
            return;
        }
        UserDefinedOperations.add(u);
        finalObservable.clear();
        inputName.clear();
        inputNumber.clear();
        createAlert(AlertType.INFORMATION, "Operation created", "The operation is created properly!", "");

    }

    /**
     * Allows the User to delete the last inserted operation during the
     * definition of a custom operation
     *
     * @return
     */
    @FXML
    private void onDeletePress(ActionEvent event) {
        if (finalObservable.size() > 0) {
            finalObservable.remove(finalObservable.size() - 1);
        }
    }

    /**
     * Allows the User to go back to the calculator's view (the first view)
     *
     * @return
     */
    @FXML
    private void onBackPress(ActionEvent event) {
        calculatorPane.setVisible(true);
        calculatorPane.setDisable(false);
        operationsPane.setVisible(false);
        operationsPane.setDisable(true);
    }

    /**
     * Allows the User to insert a supported operation in the list that gather
     * all the operations inserted by the user in the phase of definition of a
     * custom operation (the list in the bottom-right corner)
     *
     * @return
     */
    @FXML
    private void onInsertSupportedPress(ActionEvent event) {

        if (operationsList.getSelectionModel().isSelected(operationsList.getSelectionModel().getSelectedIndex())) {
            SupportedOperation op = operationsList.getSelectionModel().getSelectedItem();

            //selected th push operation
            if (op.getName().equalsIgnoreCase("push")) {
                Optional<String> result = createTextInputDialog("Push Operation", "Please, insert a complex number", "insert here:");
                if (result.isPresent()) {
                    Complex num = check.parseComplex(result.get(), "j");
                    if (num == null) {
                        createAlert(AlertType.ERROR, "Error", "Look, an Error!", "Invalid complex number inserted:\n" + result.get());
                        return;
                    }
                    finalObservable.add(new StackOperation("push", rpn, num));
                }

            } //selected a stack or an arithmetic operation
            else if (op instanceof StackOperation || op instanceof ArithmeticOperation) {
                finalObservable.add(operationsList.getSelectionModel().getSelectedItem());
            } //selected an user defined operation
            else {
                Optional<String> result = createTextInputDialog("Variable Operation", "Please, insert a variable name (a-z)", "insert here:");
                if (result.isPresent()) {
                    InputValidation i = new InputValidation();
                    String variableName = i.checkVariable(op.getName() + result.get());
                    if (variableName == null) {
                        createAlert(AlertType.ERROR, "Error", "Look, an Error!", "Invalid variable name:\n" + result.get());
                        return;
                    }
                    finalObservable.add(new VariableOperation(variableStorage, variableName.substring(1, variableName.length()), rpn, op.getName()));
                }

            }
            //finalList autoscroll enable
            finalList.scrollTo(finalList.getItems().size());

            operationsList.getSelectionModel().clearSelection();
        }

    }

    /**
     * Allows the User to insert a user defined operation in the list that
     * gather all the operations inserted by the user in the phase of definition
     * of a custom operation (the list in the bottom-right corner) (in this way
     * he creates a nested user defined operation)
     *
     * @return
     */
    @FXML
    private void onInsertDefinedPress(ActionEvent event) {
        if (userDefinedList.getSelectionModel().isSelected(userDefinedList.getSelectionModel().getSelectedIndex())) {
            finalObservable.add(userDefinedList.getSelectionModel().getSelectedItem());
            userDefinedList.getSelectionModel().clearSelection();
        }
    }

    //UTILS METHOD
    /**
     * Util method to create a text input dialog
     *
     * @return
     */
    private Optional<String> createTextInputDialog(String title, String header, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        return dialog.showAndWait();
    }

    /**
     * Util method to create an alert
     *
     * @return
     */
    private void createAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

    /**
     * Util method to populate the list with al the operations (non custom)
     * usable from the user.
     *
     * @return
     */
    private void populate() {
        String[] arithmeticOperation = {"+", "-", "*", "/", "sqrt", "+-"};
        String[] stackOperations = {"dup", "over", "clear", "drop", "swap", "push"};
        String[] variableOperations = {"+", "-", ">", "<"};
        for (String op : arithmeticOperation) {
            SupportedOperation toAdd = new ArithmeticOperation(op, rpn);
            operationsObservable.add(toAdd);
            this.supportedOperation.put(op, toAdd);
        }
        for (String op : stackOperations) {
            SupportedOperation toAdd = new StackOperation(op, rpn);
            operationsObservable.add(toAdd);
            this.supportedOperation.put(op, toAdd);
        }
        for (String op : variableOperations) {
            operationsObservable.add(new VariableOperation(variableStorage, rpn, op));
            this.supportedOperation.put(op + "var", new VariableOperation(variableStorage, rpn, op));
        }
    }

    /**
     * Allows the User to save the state of current variables.
     *
     * @return
     */
    @FXML
    private void onSavePress(ActionEvent event) throws CloneNotSupportedException, Exception {
        //there aren't variables to save
        if (columnValue.getColumns().isEmpty()) {
            return;
        }
        variableStorage.saveState();
        createAlert(AlertType.INFORMATION, "Save Variable State", "Confirmation Message", "Variables State saved properly");
    }

    /**
     * Allows the User to restore the state of variables.
     *
     * @return
     */
    @FXML
    private void onRestorePress(ActionEvent event) {
        try {
            variableStorage.restoreState();
        } catch (NoSuchElementException e) {
            createAlert(AlertType.ERROR, "Save Variable State", "Error Message", "There isn't a state to restore");
        }
    }

}
