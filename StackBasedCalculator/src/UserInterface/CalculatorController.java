package UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label lblResult;

    private double x, y;
    private double num1 = 0;
    private String operator = "+";
    private TableView<String> stackTable;
    private ObservableList<String> list;
    @FXML
    private Pane btn71111;
    @FXML
    private Pane btn711111;
    @FXML
    private Pane btn711112;
    @FXML
    private Pane btn711113;
    @FXML
    private Pane btn711114;
    @FXML
    private Pane btn7111111;
    @FXML
    private Pane btn7111121;
    @FXML
    private Pane btn7111131;
    @FXML
    private Pane btn7111141;
    @FXML
    private Pane btn7111112;
    @FXML
    private Pane btn7111122;
    @FXML
    private Pane btn7111132;
    @FXML
    private Pane btn7111142;
    @FXML
    private Pane btn7111113;
    @FXML
    private Pane btn7111123;
    @FXML
    private Pane btn7111133;
    @FXML
    private Pane btn71111111;
    @FXML
    private Pane btn71111112;
    @FXML
    private Pane btn71111113;
    @FXML
    private Pane btn711111111;
    @FXML
    private Pane btn711111121;
    @FXML
    private Pane btn711111131;
    @FXML
    private Pane btn7111111111;
    @FXML
    private Pane btn7111111211;
    @FXML
    private Pane btn7111111311;
    @FXML
    private Pane btn71111111111;
    @FXML
    private Pane btn71111112111;
    @FXML
    private Pane btn71111113111;

    
    
    
    public void init(Stage stage) {
      //  list = FXCollections.observableArrayList();
      //  stackTable.setItems(list);
      //  stackColumn.setCellValueFactory(new PropertyValueFactory<>("none"));

      //  stackColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       // list.add("1");
      //  list.add("2");
        
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        lblResult.setText(Double.parseDouble(lblResult.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(lblResult.getText())*10+value));
    }

    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")) {
            double num2 = Double.parseDouble(lblResult.getText());
            switch (operator) {
                case "+" -> lblResult.setText((num1+num2) + "");
                case "-" -> lblResult.setText((num1-num2) + "");
                case "*" -> lblResult.setText((num1*num2) + "");
                case "/" -> lblResult.setText((num1/num2) + "");
            }
            operator = ".";
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0.0));
            operator = ".";
        }
        else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";
            }
            num1 = Double.parseDouble(lblResult.getText());
            lblResult.setText(String.valueOf(0.0));
        }
    }
}
