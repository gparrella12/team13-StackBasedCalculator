package UserInterface;

import MainMathOperation.ObservableStack.ObservableStack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorController {

    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private TextArea textArea;
    @FXML
    private Button delete;

    private double x, y;
    private ObservableStack<Float> o;
    private String[] stackOperations = {"dup", "over", "clear", "drop", "swap", "sqrt"};
    private String[] mathOperations = {"+", "-", "*", "/", "sqrt"};

    public void init(Stage stage) {
        Scene scene = stage.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.BACK_SPACE && textArea.getText().length() > 0) {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
            }

            e.consume();
        });

        delete.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

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
        o = new ObservableStack<>();
    }

    @FXML
    private void Number(ActionEvent event) {

        String no = ((Button) event.getSource()).getText();
        textArea.setText(textArea.getText() + no);
    }

    @FXML
    private void Operation(ActionEvent event) {

        String no = ((Button) event.getSource()).getText();
        textArea.setText(textArea.getText() + no);
    }

    @FXML
    private void deleteLast(ActionEvent event) {
        if (textArea.getText().length() > 0) {
            textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
        }
    }

    @FXML
    private void push(ActionEvent event) {
        int flag = 1;
        float o1, o2, result = 0;

        for (String s : mathOperations) {
            if (textArea.getText().equalsIgnoreCase(s)) {

                if (s == "+") {
                    o1 = o.pop();
                    o2 = o.pop();
                    result = o1 + o2;
                } else if (s == "-") {
                    o1 = o.pop();
                    o2 = o.pop();
                    result = o1 - o2;
                } else if (s == "*") {
                    o1 = o.pop();
                    o2 = o.pop();
                    result = o1 * o2;
                } else if (s == "/") {
                    o1 = o.pop();
                    o2 = o.pop();
                    result = o1 / o2;
                } else if (s == "sqrt") {
                    o1 = o.pop();
                    result = (float) Math.sqrt(o1);
                }
                textArea.setText(String.valueOf(result));

                flag = 0;
                break;
            }
        }

        if (flag == 1) {
            o.push(Float.parseFloat(textArea.getText()));
            textArea.setText("");
        }

    }

}
