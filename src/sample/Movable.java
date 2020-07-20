package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Movable {
    private double x,y;
    @FXML
    private HBox topBar;
    @FXML
    private void dragged(MouseEvent event){
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setX(event.getScreenX()-x);
        window.setY(event.getScreenY()-y);
    }
    @FXML
    private void pressed(MouseEvent event){
        x = event.getX();
        y = event.getY();
    }
}
