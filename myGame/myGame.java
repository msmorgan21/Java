/*
* 
 * Name: Sean Morgan
 * Description: A Simple game where the player must first obtain all 3 gold coins before being able to see the finish line.
 * Win - reach the finish line after it is revealed
 * Loss - if any of the moving obstacles hit the player
 */
package myGame;
import javafx.scene.input.KeyCombination;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class myGame extends Application {

    //create circle/movement properties
    double increment = 5.0;
    double radius = 10;
    double x = radius, y = radius;
    
    //create Rectangle properties
    double Rwidth = 80;
    double Rheight = 20;
    
    //create player and objects
    Rectangle r1 = new Rectangle(0, 100, Rwidth, Rheight);//Rectangle(int x, int y, int width, int height)
    Rectangle r2 = new Rectangle(520, 100, Rwidth, Rheight);
    Rectangle r3 = new Rectangle(520, 200, Rwidth, Rheight);
    Rectangle r4 = new Rectangle(0, 300, Rwidth, Rheight);
    Rectangle r5 = new Rectangle(300, 340, Rheight, Rwidth);
    Rectangle r6 = new Rectangle(150, 150, Rheight, Rwidth);
    Rectangle r7 = new Rectangle(150, 300, Rheight, Rwidth);
    Rectangle r8 = new Rectangle(500, 300, Rheight, Rwidth);

    
    //first object
    double xObj = 0;
    double yObj = 150;
    double dx = 2;
    double dy = 2;
    Circle circle = new Circle(xObj, yObj, 10);
    
    //2nd object
    double xObj2 = 150;
    double yObj2 = 150;
    double dx2 = 4;
    double dy2 = 2;
    Circle circle2 = new Circle(xObj2, yObj2, 10);


    double xObj3 = 150;
    double yObj3 = 300;
    double dx3 = 4;
    double dy3 = 2;
    Circle circle3 = new Circle(xObj3, yObj3, 10);

    double xObj4 = 150;
    double yObj4 = 300;
    double dx4 = 4;
    double dy4 = 2;
    Circle circle4 = new Circle(xObj4, yObj4, 10);

   //coin properties
    Circle coin = new Circle(200, 200, 10, Color.GOLD);
    Circle coin2 = new Circle(300, 300, 10, Color.GOLD);
    Circle coin3 = new Circle(400, 400, 10, Color.GOLD);

    TableView tableView;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Create the player
        Circle player = new Circle(x, y, radius, Color.RED);
        
        // Create the Finish Line
        Rectangle finishLine = new Rectangle(581, 520, Rheight, Rwidth);
        Image finishImage = new Image("finishLine.png");//Image is from http://governanceconnect.eu/product/black-white/chess-board-black-white/
        finishLine.setFill(new ImagePattern(finishImage));
        finishLine.setVisible(false);
        
        // Create the panes to handle the game
        BorderPane mainPane = new BorderPane();
        Pane gameField = new Pane();
        
        // Define the properties of the Panes
        mainPane.setMaxSize(800, 800);
        mainPane.setCenter(gameField);       
        gameField.setMaxSize(600, 600);
        gameField.setStyle("-fx-background-color: blue");
        gameField.setStyle("-fx-border-color: black");
        gameField.getChildren().addAll(r1, r2, r3, r4, r5,r6,r7,
        player, circle, circle2, circle3, circle4, coin, coin2, coin3, finishLine);
        
        // Create the Scene
        Scene scene = new Scene(mainPane, 900,900);
        stage.setScene(scene);
        stage.show();
        
        // Methods for handling user Interaction
        moveCircleOnKeyPress(scene, player, gameField, finishLine);
        menuBar(mainPane, player, finishLine);
        objectMovement(player,gameField, finishLine);       
    }
    
    // Create the method to handle the movement of the objects
    public void objectMovement(Circle player, Pane gameField, Rectangle finishLine){
         
        Timeline obj1 = new Timeline(
                new KeyFrame(Duration.millis(20), (ActionEvent e) -> {
                    if ((circle.getCenterX() + circle.getRadius()) < gameField.getMaxWidth()) {//if circle is inside the gameField move it to the right
                        dx++;
                    }
                    if ((circle.getCenterX() - circle.getRadius()) > gameField.getMinWidth()) {//if circle is inside the gameField move it to the left
                        dx--;
                    }
                    if (circle.getCenterX() > r3.getY()) {//if circle hits r3 move backwards
                        dx--;
                    }

                    if (circle.intersects(player.getBoundsInLocal())) {//if player hits the object
                        player.centerXProperty().setValue(0);
                        player.centerYProperty().setValue(0);

                        Stage lostWindow = new Stage();
                        lostWindow.setScene(new Scene(new Label("Whoops! Try again!")));
                        lostWindow.show();
//                        lostWindow.setOnCloseRequest((WindowEvent event) -> {
//                            Close();
//                        });

                    }

                    xObj += dx;
                    circle.setCenterX(xObj);
                    circle.setCenterY(yObj);
                }));
        obj1.setCycleCount(Timeline.INDEFINITE);
        obj1.play(); // Start animation

        Timeline obj2 = new Timeline(
                new KeyFrame(Duration.millis(20), (ActionEvent e) -> {
                        if ((circle2.getCenterX() + circle2.getRadius()) < gameField.getMaxWidth()) {
                            dx2++;
                        }
                        if ((circle2.getCenterX() - circle2.getRadius()) > gameField.getMinWidth()) {
                            dx2--;
                        }
                        if (circle2.intersects(player.getBoundsInLocal())) {
                            player.centerXProperty().setValue(0);
                            player.centerYProperty().setValue(0);

                            Stage lostWindow = new Stage();
                            lostWindow.setScene(new Scene(new Label("Whoops! Sorry you lose")));
                            lostWindow.show();

                        }

                        xObj2 += dx2;
                        circle2.setCenterX(xObj2);
                        circle2.setCenterY(yObj2);                  
                }));
        obj2.setCycleCount(Timeline.INDEFINITE);
        obj2.play();

        Timeline obj3 = new Timeline(
                new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if ((circle3.getCenterX() + circle3.getRadius()) < gameField.getMaxWidth()) {
                            dx3++;
                        }
                        if ((circle3.getCenterX() - circle3.getRadius()) > gameField.getMinWidth()) {
                            dx3--;
                        }
                        if (circle3.getCenterX() - circle3.getRadius() > 300) {
                            dx3--;
                        }
                        if (circle3.intersects(player.getBoundsInLocal())) {

                            player.centerXProperty().setValue(0);
                            player.centerYProperty().setValue(0);

                            Stage lostWindow = new Stage();
                            lostWindow.setScene(new Scene(new Label("Whoops! Sorry you lose")));
                            lostWindow.show();
                        }

                        xObj3 += dx3;
                        circle3.setCenterX(xObj3);
                        circle3.setCenterY(yObj3);
                    }
                }));
        obj3.setCycleCount(Timeline.INDEFINITE);
        obj3.play();

        Timeline obj4 = new Timeline(
                new KeyFrame(Duration.millis(20), (ActionEvent e) -> {
                    if ((circle4.getCenterX() + circle4.getRadius()) < gameField.getMaxWidth()) {
                        dx4++;
                    }
                    if ((circle4.getCenterX() - circle4.getRadius()) > gameField.getMinWidth()) {
                        dx4--;
                    }
                    if ((circle4.getCenterX() - circle4.getRadius()) < 100) {
                        dx4++;
                    }
                    if (circle4.intersects(player.getBoundsInLocal())) {
                        player.centerXProperty().setValue(0);
                        player.centerYProperty().setValue(0);
                        
                        Stage lostWindow = new Stage();
                        lostWindow.setScene(new Scene(new Label("Whoops! Sorry you lose")));
                        lostWindow.show();
                    }
                    xObj4 += dx4;
                    circle4.setCenterX(xObj4);
                    circle4.setCenterY(yObj4);
        }));
        obj4.setCycleCount(Timeline.INDEFINITE);
        obj4.play();
    }
    
    // Create the method to handle player movement
    public void moveCircleOnKeyPress(Scene scene, Circle player, Pane gameField, Rectangle finishLine) {//player movement
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:
                    player.setCenterY(player.getCenterY() - increment);//move player
                    checkBounds(player, gameField);//check boundaries   
                    coins(player, finishLine);//check for coin gathering
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
                    if (Shape.intersect(player, r6).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterY(player.getCenterY() + increment);
                    }
                    if (Shape.intersect(player, r7).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterY(player.getCenterY() + increment);
                    }
                    break;
                case RIGHT:
                    player.setCenterX(player.getCenterX() + increment);
                    checkBounds(player, gameField);//check boundaries   
                    coins(player, finishLine);//check for coin gathering
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
                    if (Shape.intersect(player, r6).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterX(player.getCenterX() - increment);
                    }
                    if (Shape.intersect(player, r7).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterX(player.getCenterX() - increment);
                    }
                    
                    if (player.getBoundsInLocal().intersects(finishLine.getBoundsInLocal())) {
                        Stage winWindow = new Stage();
                        winWindow.setScene(new Scene(new Label("Congrats! you made it!")));  
                        winWindow.showAndWait();
                        Close();
                    }

                    break;
                case DOWN:
                    player.setCenterY(player.getCenterY() + increment);
                    checkBounds(player, gameField);//check boundaries                  
                    coins(player, finishLine);//check for coin gathering
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
                    if (Shape.intersect(player, r6).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterY(player.getCenterY() - increment);
                    }
                    if (Shape.intersect(player, r7).getBoundsInLocal().getWidth() != -1) {
                        player.setCenterY(player.getCenterY() - increment);
                    }

                    break;
                case LEFT:
                    player.setCenterX(player.getCenterX() - increment);
                    checkBounds(player,gameField);//check boundaries  
                    coins(player, finishLine);//check for coin gathering
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
                    if (Shape.intersect(player, r6).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r3 intersect
                        player.setCenterX(player.getCenterX() + increment);
                    }
                    if (Shape.intersect(player, r7).getBoundsInLocal().getWidth() != -1) {//if the Shapes of player and r3 intersect
                        player.setCenterX(player.getCenterX() + increment);
                    }
                    break;
            }
        });
    }
    
    // Check the bounds to make sure the player stays within the gamefield.
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
    
    // Coin Method
    public void coins(Circle player, Rectangle finishLine) {

        if (player.intersects(coin.getBoundsInLocal())) {
            coin.setVisible(false);
            coin2.setVisible(true);
        }
        if (player.intersects(coin2.getBoundsInLocal())) {
            coin2.setVisible(false);//hide coin2  
        }
        if (player.intersects(coin3.getBoundsInLocal())) {
            coin3.setVisible(false);//hide coin        
        }
        
        //if all 3 coins have been collected, show the finish line
        if(coin.isVisible() == false && coin2.isVisible() == false && coin3.isVisible() == false){
            finishLine.setVisible(true);
        }
    }
    
    // Create the menubar
    public void menuBar(BorderPane mainPane, Circle player, Rectangle finishLine){
        MenuBar menu = new MenuBar();
        Menu options = new Menu("Options");
        MenuItem close = new MenuItem("close");
        MenuItem newGame = new MenuItem("New Game");
        
        //add menuItems to menu
        options.getItems().addAll(close, newGame);
        menu.getMenus().addAll(options);//add menuItems to MenuBar.
        mainPane.setTop(menu);//add menubar to the top of the borderPane
        
        //to close the game application
        close.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
        close.setOnAction((ActionEvent event) -> {
            Close();
        });
        
        //to start a new game
        newGame.setAccelerator(KeyCombination.keyCombination("Ctrl + N"));
        newGame.setOnAction((ActionEvent event) -> {
            newGame(player, finishLine);
        });
    }
    
    // End program
    public void Close() {
        System.exit(0);
    }
    
    // Begin a New Game
    public void newGame(Circle player, Rectangle finishLine) {
        //reset player
        player.setCenterX(0 + radius);
        player.setCenterY(0 + radius);
        
        //reset coins
        coin.setVisible(true);
        coin2.setVisible(true);
        coin3.setVisible(true);
        finishLine.setVisible(false);
    }
}
