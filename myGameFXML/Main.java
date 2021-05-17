/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGameFXML;


import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[]args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "D:\\Classes\\CITC1311\\src\\myGameFXML\\myGameFXML.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        
        // Now Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        
        // Create the Scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Game");
        stage.show();
    }

}
