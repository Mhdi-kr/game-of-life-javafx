package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private int state;
    private int i,j;
    public Cell(int i,int j){
        this.i = i;
        this.j = j;
        this.setWidth(20);
        this.setHeight(20);
        this.state = 0;
        this.setOnMousePressed(mouseEvent -> {
            if(this.getState() == 1){
                this.kill();
            } else {
                this.revive();
            }
        });
    }
    public int getState(){
        return this.state;
    }
    public void setState(int i){
        this.state = i;
        System.out.println("changed" + this.i + this.j + " to " + this.state);
    }
    public int getXCordinate(){
        return this.i;
    }
    public int getYCordinate(){
        return this.j;
    }
    public void revive(){
        this.setState(1);
        this.setFill(Color.WHITE);
    }

    public void kill() {
        this.setState(0);
        this.setFill(Color.BLACK);
    }
}
