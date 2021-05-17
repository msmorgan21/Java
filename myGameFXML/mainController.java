package myGameFXML;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class mainController implements Initializable {

    
    @FXML
    Circle player, circle, circle2, coin, coin2, coin3;
    @FXML
    Rectangle r1, r2, r3, r4, r5;
    @FXML
    Pane gameField;
    @FXML 
    Rectangle finishLine;
    Image image = new Image(new File("D:\\Classes\\CITC1311\\src\\myGameFXML\\finishLine.png").toURI().toString());
    
    
    double dx = 4;
    double dy = 2;
    double dx2 = 4;
    double dy2 = 2;
    
    @FXML
    public void keyHandler(KeyEvent event) {
        
        //Set the movement speed
        double increment = 10;
        
        switch (event.getCode()) {
            case UP:
                player.setCenterY(player.getCenterY() - increment);
                checkBounds(player, gameField);
                coins(player,finishLine);
                
                if (Shape.intersect(player, r1).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() + increment);
                }
                if (Shape.intersect(player, r2).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() + increment);
                }
                if (Shape.intersect(player, r3).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() + increment);
                }
                if (Shape.intersect(player, r4).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() + increment);
                }
                if (Shape.intersect(player, r5).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() + increment);
                }
                break;
                
            case RIGHT:
                player.setCenterX(player.getCenterX() + increment);
                checkBounds(player, gameField);
                coins(player,finishLine);
                
                if (Shape.intersect(player, r1).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterX(player.getCenterX() - increment);
                }
                if (Shape.intersect(player, r2).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterX(player.getCenterX() - increment);
                }
                if (Shape.intersect(player, r3).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterX(player.getCenterX() - increment);
                }
                if (Shape.intersect(player, r4).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterX(player.getCenterX() - increment);
                }
                if (Shape.intersect(player, r5).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterX(player.getCenterX() - increment);
                }
                if((Shape.intersect(player, finishLine).getBoundsInLocal().getWidth() != -1)){
                    System.out.println("You made it!");
                }
                break;
                
            case DOWN:
                player.setCenterY(player.getCenterY() + increment);
                checkBounds(player, gameField);
                coins(player,finishLine);
                
                if (Shape.intersect(player, r1).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() - increment);
                }
                if (Shape.intersect(player, r2).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() - increment);
                }
                if (Shape.intersect(player, r3).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() - increment);
                }
                if (Shape.intersect(player, r4).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() - increment);
                }
                if (Shape.intersect(player, r5).getBoundsInLocal().getWidth() != -1) {
                    player.setCenterY(player.getCenterY() - increment);
                }
                break;
                
            case LEFT:
                player.setCenterX(player.getCenterX() - increment);
                checkBounds(player, gameField);
                coins(player,finishLine);
                
                if (Shape.intersect(player, r1).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r1 intersect
                    player.setCenterX(player.getCenterX() + increment);
                }
                if (Shape.intersect(player, r2).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r2 intersect
                    player.setCenterX(player.getCenterX() + increment);
                }
                if (Shape.intersect(player, r3).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r3 intersect
                    player.setCenterX(player.getCenterX() + increment);
                }
                if (Shape.intersect(player, r4).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r3 intersect
                    player.setCenterX(player.getCenterX() + increment);
                }
                if (Shape.intersect(player, r5).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r3 intersect
                    player.setCenterX(player.getCenterX() + increment);
                }
                break;
        }
    }

    @FXML
    public void checkBounds(Circle player, Pane gameField) {
        if (player.getCenterY() <= gameField.getMinHeight()) {//if player tries to pass the upper edge
            player.setCenterY(gameField.getMinHeight() + player.getRadius());//set player to the panes edge
        }
        if (player.getCenterY() >= gameField.getMaxHeight()) {//if player tries to pass the lower edge
            player.setCenterY(gameField.getMaxHeight() - player.getRadius());
        }
        if (player.getCenterX() >= gameField.getMaxWidth()) {//if player tries to pass the right edge
            player.setCenterX(gameField.getMaxWidth() - player.getRadius());
        }
        if (player.getCenterX() <= gameField.getMinWidth()) {//if player tries to pass the left edge
            player.setCenterX(gameField.getMinWidth() + player.getRadius());
        }
    }
    public void coins(Circle player, Rectangle finishLine) {

        if(Shape.intersect(player, coin).getBoundsInLocal().getWidth() != -1){
            coin.setVisible(false);

        }
        if(Shape.intersect(player, coin2).getBoundsInLocal().getWidth() != -1){
            coin2.setVisible(false);
            
        }
        if(Shape.intersect(player, coin3).getBoundsInLocal().getWidth() != -1){
            coin3.setVisible(false);
        }

       //if all 3 coins have been collected, show the finish line
        if(coin.isVisible() == false && coin2.isVisible() == false && coin3.isVisible() == false){
            finishLine.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        finishLine.setFill(new ImagePattern(image));
        
        Timeline obj1 = new Timeline(
                new KeyFrame(Duration.millis(20), (ActionEvent e) -> {

                    if ((circle.getCenterX() + circle.getRadius() * 2 * Math.PI) < gameField.getMaxWidth()) {//if circle is inside the gameField move it to the right
                        dx++;
                    }
                    if ((circle.getCenterX() - circle.getRadius() ) > gameField.getMinWidth()) {//if circle is inside the gameField move it to the left
                        dx--;
                    }
                    if (Shape.intersect(player, circle).getBoundsInLocal().getWidth() != -1) {//if player hits the object
                        player.centerXProperty().setValue(0);
                        player.centerYProperty().setValue(0);
                    }
                    circle.setCenterX(circle.getCenterX() + dx);
                }));
        obj1.setCycleCount(Timeline.INDEFINITE);
        obj1.play();           

        Timeline obj2 = new Timeline(
                new KeyFrame(Duration.millis(20), (ActionEvent e) -> {

                    if ((circle2.getCenterX() + circle2.getRadius() * 2 * Math.PI ) < gameField.getMaxWidth()) {
                        dx2++;
                    }
                    if ((circle2.getCenterX() - circle2.getRadius()) > gameField.getMinWidth()) { 
                        dx2--;       
                    }
                    if (Shape.intersect(circle2, r5).getBoundsInLocal().getWidth() != -1) {
                    dx2--;
                    }
                    if(Shape.intersect(player, circle2).getBoundsInLocal().getWidth() != -1){
                        player.centerXProperty().setValue(0);
                        player.centerYProperty().setValue(0);
                    }

                    circle2.setCenterX(circle2.getCenterX() + dx2);
                }));
        obj2.setCycleCount(Timeline.INDEFINITE);
        obj2.play();
        
        
                
    }
}
