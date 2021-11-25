package UserInterface;

import MainMathOperation.RPNSolver;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.math3.complex.Complex;

/**
 * Implementation of the Calculator User Interface Controller
 *
 * @authors emancusi & Speranza
 */
public class CalculatorController {

    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btnClearEntry;

    private double x, y;
    private CheckInputKeyboard check;
    private RPNSolver rpn;
    @FXML
    private ListView<Complex> stackList;
    @FXML
    private Button btnPush;
    @FXML
    private Text textWarning;

    /**
     * Initializes the User Interface. It's executed as soon as the program
     * starts.
     *
     * @return
     */
    public void init(Stage stage) {

        Scene scene = stage.getScene();
        check = new CheckInputKeyboard();
        rpn = RPNSolver.getInstance();
        // Set list cell for complex number visualization
        stackList.setCellFactory(new NumberCellFactory());
        rpn.setList(stackList);
        // Set bindings for warning
        textWarning.visibleProperty().bind(Bindings.size(stackList.getItems()).lessThan(2).and(textArea.textProperty().isEqualTo("+").or(textArea.textProperty().isEqualTo("-").or(textArea.textProperty().isEqualTo("*").or(textArea.textProperty().isEqualTo("/").or(textArea.textProperty().isEqualTo("clear").or(textArea.textProperty().isEqualTo("swap").or(textArea.textProperty().isEqualTo("over").or(Bindings.size(stackList.getItems()).lessThan(1).and(textArea.textProperty().isEqualTo("+-").or(textArea.textProperty().isEqualTo("sqrt").or(textArea.textProperty().isEqualTo("drop").or(textArea.textProperty().isEqualTo("dup"))))))))))))));

        btnPush.disableProperty().bind(Bindings.createBooleanBinding(()
                -> textArea.getText().trim().isEmpty(),
                textArea.textProperty()));
        //when the user presses the "back space" button on physical keyboard
        //the last element in the Text Area is deleted.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER && textArea.getText().length() > 0) {
                try {
                    push(new ActionEvent());
                } catch (ExecutionControl.NotImplementedException ex) {
                    Logger.getLogger(CalculatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (e.getCode() == KeyCode.BACK_SPACE && textArea.getText().length() > 0) {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
                textArea.end();
            }

            e.consume();
        });

        //if the user presses the "back space" button oh physical keyboard
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

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
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
     * When the "push" (↑) button is pressed, the item in the Text Area is
     * pushed in the stack. The function checks if the input is in a right
     * format and checks if the user enters the operations supported by the
     * Calculator.
     *
     * @return
     */
    @FXML
    private void push(ActionEvent event) throws ExecutionControl.NotImplementedException {

        String input = textArea.getText();
        String operation = check.checkOperation(input);
        textArea.clear();

        try {
            rpn.addNum(input);
            stackList.scrollTo(stackList.getItems().size());
            return;
        } catch (Exception e) {
            if (operation == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid input:\n" + input, ButtonType.OK).showAndWait();
                return;
            }
        }

        try {
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
                    return;
                case "drop":
                    rpn.drop();
                    return;
                case "swap":
                    rpn.swap();
                    return;
                case "over":
                    rpn.over();
                    return;
            }
        } catch (NoSuchElementException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid operands for this operation", ButtonType.OK).showAndWait();
            return;
        }
    }
}
