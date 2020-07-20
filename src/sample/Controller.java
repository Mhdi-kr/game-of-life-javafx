package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends Movable implements Initializable {
    public Button exitBtn;
    public Button stepBtn;
    private GridPane gridPane;
    @FXML
    private Button startBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button step;
    @FXML
    private VBox gridBox;
    private final int MAX_WIDTH = 41;
    private final int MAX_HEIGHT = 16;
    private Cell[][] cells = new Cell[MAX_WIDTH][MAX_HEIGHT];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startBtn.setDisable(true);
        init(cells);
        render(cells);
        printArray(cells);
    }

    public void start(ActionEvent actionEvent) {
    }

    public void step(ActionEvent actionEvent) {
        cells = process(cells);
        render(cells);
    }
    @FXML
    public void reset(ActionEvent actionEvent) {
        init(cells);
        render(cells);
    }
    private void render(Cell[][] cells){
        gridBox.getChildren().removeAll(gridPane);
        gridPane = new GridPane();
        for(int i=0; i<MAX_WIDTH; i++){
            for(int j=0; j< MAX_HEIGHT; j++){
                gridPane.add(cells[i][j],i,j,1,1);
            }
        }
        gridBox.getChildren().add(gridPane);
    }
    private void init(Cell[][] cells){
        for(int i=0; i<MAX_WIDTH; i++){
            for(int j=0; j<MAX_HEIGHT; j++){
                cells[i][j] = new Cell(i,j);
            }
        }
    }
    private Cell[][] process(Cell[][] currentCellArray){
        Cell[][] newCellArray = new Cell[MAX_WIDTH][MAX_HEIGHT];
        init(newCellArray);
        for(int i=1; i<MAX_WIDTH-1; i++){
            System.out.println("row" + i);
            for(int j=1; j<MAX_HEIGHT-1; j++){
                int count = 0;
                count += currentCellArray[i-1][j-1].getState();
                count += currentCellArray[i][j-1].getState();
                count += currentCellArray[i+1][j-1].getState();

                count += currentCellArray[i-1][j].getState();
                count += currentCellArray[i+1][j].getState();

                count += currentCellArray[i-1][j+1].getState();
                count += currentCellArray[i][j+1].getState();
                count += currentCellArray[i+1][j+1].getState();

                if(currentCellArray[i][j].getState() == 0 && count == 3){
                    newCellArray[i][j].revive();
                } else if(currentCellArray[i][j].getState() == 1 && (count > 3 || count < 2)){
                    newCellArray[i][j].kill();
                } else {
                    newCellArray[i][j] = currentCellArray[i][j];
                }
            }
        }
        printArray(newCellArray);
        return newCellArray;
    }
    private void printArray(Cell[][] cellArray){
        for(int i=0; i<MAX_WIDTH; i++){
            System.out.print("|");
            for(int j=0; j<MAX_HEIGHT; j++){
                if(cellArray[i][j].getState() == 1){
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println("|");
        }
    }

    public void exit(ActionEvent actionEvent) {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }
}
