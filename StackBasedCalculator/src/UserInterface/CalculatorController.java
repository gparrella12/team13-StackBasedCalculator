package UserInterface;

import MainMathOperation.RPNSolver;
import UserDefinedOperation.ArithmeticOperation;
import UserDefinedOperation.Operation;
import UserDefinedOperation.StackOperation;
import UserDefinedOperation.SupportedOperation;
import UserDefinedOperation.UserDefinedOperation;
import UserDefinedOperation.VariableOperation;
import VariablesManager.VariablesStorage;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
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
    private Pane titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btnClearEntry;
    @FXML
    private ListView<Complex> stackList;
    @FXML
    private Button btnPush;
    @FXML
    private Text textWarning;
    @FXML
    private Text textWarningSoft;
    @FXML
    private TableView<String> tableVariables;
    @FXML
    private TableColumn<String, String> clnVariable;
    @FXML
    private TableColumn<String, Complex> clnValue;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnRestore;

    //useful variables
    private double x, y;
    private InputValidation check;
    private RPNSolver rpn;
    private VariablesStorage var;
    @FXML
    private Pane calculatorPane;
    @FXML
    private Pane operationsPane;
    @FXML
    private ListView<SupportedOperation> operationsList;

    @FXML
    private ListView<UserDefinedOperation> userDefinedList;
    @FXML
    private ListView<UserDefinedOperation> definedOperationsList;

    @FXML
    private ListView<Operation> finalList;

    @FXML
    private Button btnFinalCreate;
    @FXML
    private Button btnBack;

    @FXML
    private TextField inputNumber;
    @FXML
    private TextField inputName;

    private ObservableList<UserDefinedOperation> UserDefinedOperations;
    private ObservableList<Operation> finalObservable;
    private ObservableList<SupportedOperation> operationsObservable;
    @FXML
    private Button btnInsertSupported;
    @FXML
    private Button btnInsertDefined;

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

        // Set list cell for complex number visualization
        stackList.setCellFactory(new NumberCellFactory());
        rpn.setList(stackList);

        //set table cell columns for variables visualization
        var = new VariablesStorage();
        clnValue.setCellFactory(new ColumnCellFactory());
        var.setObserver(tableVariables, clnVariable, clnValue);

        //disable buttons that will be developed in the next Sprint
        btnSave.setDisable(true);
        btnRestore.setDisable(true);

        // Set bindings for warning
        BooleanBinding oneElements = Bindings.size(stackList.getItems()).
                isEqualTo(1).and(textArea.textProperty().isEqualTo("swap").
                or(textArea.textProperty().isEqualTo("over")));

        BooleanBinding twoElements = Bindings.size(stackList.getItems()).
                lessThan(2).and(textArea.textProperty().isEqualTo("+").
                or(textArea.textProperty().isEqualTo("-").
                        or(textArea.textProperty().isEqualTo("/").
                                or(textArea.textProperty().isEqualTo("*")))));

        BooleanBinding emptyList = Bindings.size(stackList.getItems()).
                isEqualTo(0).and(textArea.textProperty().isEqualTo("+-").
                or(textArea.textProperty().isEqualTo("sqrt")));

        textWarning.visibleProperty().bind(oneElements.or(twoElements).or(emptyList));

        textWarningSoft.visibleProperty().bind(Bindings.size(stackList.getItems()).
                greaterThan(0).and(textArea.textProperty().isEqualTo("clear")));

        //push button is disabled when the Text Area is empty
        btnPush.disableProperty().bind(Bindings.createBooleanBinding(()
                -> textArea.getText().trim().isEmpty(),
                textArea.textProperty()));

        //when the user presses the "back space" button on physical keyboard
        //the last element in the Text Area is deleted.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER && textArea.getText().length() > 0) {
                push(new ActionEvent());

            }
            if (e.getCode() == KeyCode.BACK_SPACE && textArea.getText().length() > 0) {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
                textArea.end();
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
                        textArea.setText("");
                    }
                }
            }
        });

        //allows the user to move around, on the screen, the calculator
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        //close the Calculator
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        //minimize the Calculator
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        finalObservable = FXCollections.observableArrayList();
        operationsObservable = FXCollections.observableArrayList();
        UserDefinedOperations = FXCollections.observableArrayList();

        userDefinedList.setItems(UserDefinedOperations);
        definedOperationsList.setItems(UserDefinedOperations);
        finalList.setItems(finalObservable);
        operationsList.setItems(operationsObservable);
        populate();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
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

        btnFinalCreate.disableProperty().bind((Bindings.size(finalObservable).isEqualTo(0)).or(inputName.textProperty().isEmpty()).or(inputNumber.textProperty().isEmpty()));
        btnBack.disableProperty().bind((Bindings.size(finalObservable).isEqualTo(0)));
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
        textArea.setText(textArea.getText() + number);
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
        textArea.setText(textArea.getText() + operation);
    }

    /**
     * When the "Clear entry" (⌫) button is pressed, the last item of the Text
     * Area is cleaned up.
     *
     * @return
     */
    @FXML
    private void deleteLast(ActionEvent event) {

        if (textArea.getText().length() > 0) {
            textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
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
        String input = textArea.getText();
        String operation = check.checkOperation(input);
        String supportedVariable = check.checkVariable(input);

        textArea.clear();

        try {
            //add a number in the stack
            rpn.addNum(check.parser(input, "j"));
            //update the Stack view and scroll the list to the last element
            stackList.scrollTo(stackList.getItems().size());
            return;
        } catch (Exception e) {
            if (operation == null && supportedVariable == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Look, an Error!");
                alert.setContentText("Invalid input:\n" + input);
                alert.showAndWait();
                return;
            }
        }

        try {
            //according to the operation entered by the user
            //perform the corresponding operation
            if (operation != null) {
                switch (operation) {
                    case "+":
                        rpn.sum();
                        return;
                    case "-":
                        rpn.subtraction();
                        return;
                    case "*":
                        rpn.product();
                        return;
                    case "/":
                        rpn.division();
                        return;
                    case "sqrt":
                        rpn.sqrt();
                        return;
                    case "+-":
                        rpn.invertSign();
                        return;
                    case "clear":
                        rpn.clear();
                        return;
                    case "dup":
                        rpn.dup();
                        stackList.scrollTo(stackList.getItems().size());
                        return;
                    case "drop":
                        rpn.drop();
                        return;
                    case "swap":
                        rpn.swap();
                        return;
                    case "over":
                        rpn.over();
                        stackList.scrollTo(stackList.getItems().size());
                        return;
                }
            }
        } catch (NoSuchElementException | ArithmeticException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Look, an Error!");
            alert.setContentText("Invalid operands for this operation");
            alert.showAndWait();
            return;
        }

        try {
            //according to the operation entered by the user
            //perform the corresponding operation
            if (supportedVariable != null) {
                String varOperation = supportedVariable.substring(0, 1);
                String variable = supportedVariable.substring(1);
                switch (varOperation) {
                    // >x: takes the top element from the stack
                    // and saves it into the variable "x".
                    case ">":
                        var.save(variable, rpn.getAns());
                        return;
                    // <x: pushes the value of the variable "x" onto the stack.
                    case "<":
                        Complex num = var.getVariableValue(variable);
                        rpn.addNum(num);
                        return;
                    // +x: takes the top element from the stack and adds it
                    // to the value of the variable "x"
                    case "+":
                        var.addToVariable(variable, rpn.getAns());
                        return;
                    // -x: takes the top element from the stack and subtracts it
                    // from the value of the variable "x"
                    case "-":
                        var.subFromVariable(variable, rpn.getAns());
                        return;
                }
            }
        } catch (NoSuchElementException | ArithmeticException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Look, an Error!");
            alert.setContentText("Invalid operands for this operation");
            alert.showAndWait();
            return;
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
     * Allows the User to save the state of current variables.
     *
     * @return
     */
    @FXML
    private void onSavePress(ActionEvent event) {

    }

    /**
     * Allows the User to restore the state of variables.
     *
     * @return
     */
    @FXML
    private void onRestorePress(ActionEvent event) {

    }

    @FXML
    private void onFinalCreatePress(ActionEvent event) {
        String name = inputName.getText();

        if (name.contains("$") || name.contains("£")
                || name.contains("#") || name.contains("!")
                || name.contains("?") || name.contains("%") || name.contains("&")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Look, an Error!");
            alert.setContentText("The operation name can’t contain special characters ($, £,#,!,?,%,&)");
            alert.showAndWait();
            return;
        }
        int operatorsNumber = Integer.parseInt(inputNumber.getText());

        UserDefinedOperation u = new UserDefinedOperation(name, operatorsNumber, finalObservable.stream().collect(Collectors.toList()));

        if (UserDefinedOperations.contains(u)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Look, an Error!");
            alert.setContentText("An operation with this name already exists, please change it.");
            alert.showAndWait();
            return;
        }
        UserDefinedOperations.add(u);
        finalObservable.clear();
        inputName.clear();
        inputNumber.clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Operation created");
        alert.setContentText("The operation is created properly!");
        alert.showAndWait();
    }

    @FXML
    private void onDeletePress(ActionEvent event) {
        if (finalObservable.size() > 0) {
            finalObservable.remove(finalObservable.size() - 1);
        }
    }

    private void populate() {
        String[] arithmeticOperation = {"+", "-", "*", "/", "sqrt", "+-"};
        String[] stackOperations = {"dup", "over", "clear", "drop", "swap", "push"};
        String[] variableOperations = {"+", "-", ">", "<"};
        for (String op : arithmeticOperation) {
            operationsObservable.add(new ArithmeticOperation(op, rpn));
        }
        for (String op : stackOperations) {
            operationsObservable.add(new StackOperation(op, rpn));
        }
        //for (String op : variableOperations) {
        //    operationsObservable.add(new VariableOperation(op, rpn));
        // }
    }

    @FXML
    private void onBackPress(ActionEvent event) {
        calculatorPane.setVisible(true);
        calculatorPane.setDisable(false);
        operationsPane.setVisible(false);
        operationsPane.setDisable(true);
    }

    @FXML
    private void onInsertSupportedPress(ActionEvent event) {

        if (operationsList.getSelectionModel().isSelected(operationsList.getSelectionModel().getSelectedIndex())) {
            SupportedOperation op = operationsList.getSelectionModel().getSelectedItem();
            if (op.getName().equalsIgnoreCase("push")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Push Operation");
                dialog.setHeaderText("Please, insert a complex number");
                dialog.setContentText("insert here:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    InputValidation i = new InputValidation();
                    Complex num = i.parser(result.get(), "j");
                    if (num == null) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Look, an Error!");
                        alert.setContentText("Invalid complex number inserted!");
                        alert.showAndWait();
                        return;
                    }
                    finalObservable.add(new StackOperation("push", rpn, num));
                }

            } else {
                finalObservable.add(operationsList.getSelectionModel().getSelectedItem());
            }

            operationsList.getSelectionModel().clearSelection();
        }

    }

    @FXML
    private void onInsertDefinedPress(ActionEvent event) {

        if (userDefinedList.getSelectionModel().isSelected(userDefinedList.getSelectionModel().getSelectedIndex())) {
            finalObservable.add(userDefinedList.getSelectionModel().getSelectedItem());
            userDefinedList.getSelectionModel().clearSelection();
        }
    }
}
