package UserInterface;

import MainMathOperation.RPNSolver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML
    private TableView<String> stackTable;
    @FXML
    private TableColumn<?, ?> stackCol;

    private double x, y;
    private CheckInputKeyboard check;
    private RPNSolver rpn;

    /**
     * Initializes the User Interface. It's executed as soon as the program
     * starts.
     *
     * @return
     */
    public void init(Stage stage) {
        Scene scene = stage.getScene();
        check = new CheckInputKeyboard();

        //when the user presses the "back space" button on physical keyboard
        //the last element in the Text Area is deleted.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.BACK_SPACE && textArea.getText().length() > 0) {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
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
    private void push(ActionEvent event) {
        String input = textArea.getText();
        if (check.checkIfComplex(input)) {
            rpn.addNum(input.replace("j", "i"));
            return;
        }
        String operation = check.checkOperation(input);
        else if (operation != null) {              
                    switch (operation) {
                        case "+":
                           rpn.sum();
                            break;
                        case "-":
                           rpn.subtraction();
                           break;
                        case "*":
                            rpn.product();
                            break;
                         case "/":
                            rpn.product();
                            break;
                         case "sqrt":
                            rpn.sqrt();
                            break;    
                        case "clear":
                            rpn.clear();
                            break;    
                        case "dup":
                            rpn.dup();
                            break;  
                        case "drop":
                            rpn.drop();
                            break;  
                        case "swap":
                            rpn.swap();
                            break;  
                        case "over":
                            rpn.over();
                            break;  
                    }

      

                    }
    }

}
