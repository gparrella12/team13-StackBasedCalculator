package UserInterface;

import MainMathOperation.ObservableStack.ObservableStack;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorController {

    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
    private Label lblResult;

    private double x, y;
    private double num1 = 0;
    private String operator = "+";
    private ObservableStack<String> o;

    @FXML
    private TextArea textArea;
    @FXML
    private Button btn7;
    @FXML
    private Button delete;

    public void init(Stage stage) {
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
        if (textArea.getText().length()>0)
        textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
    }

    @FXML
    private void push(ActionEvent event) {

    }

}
