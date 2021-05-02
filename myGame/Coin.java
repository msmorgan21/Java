/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAB8;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author morga
 */
public class Coin extends Circle {

    // Set the initial number of objects to be created
//    int numberOfCoins = 0;
    Color color;
    double radius;
    double x;
    double y;
    
    // Create Constructor
    Coin(){
        radius = 1;
    }
    
    // Create a Coin with Specified values
    Coin(double newRadius, double xPosition, double yPosition){
        radius = newRadius;

        x = xPosition;
        y = yPosition;
    }
    
    // Get Color
//    public Color getColor(){
//      return Color.GOLD;
//    }
//    
//    // Set Color
//    public void setColor(Color newColor){
//        color = newColor;
//    }
    public void setX(double newX){
        x = newX;
    }
    
    public void setY(double newY){
        y = newY;
    }
    // Return the area of the Coin 
  double getArea() {
    return radius * radius * Math.PI;
  }

  // Return the perimeter of the Coin 
  double getPerimeter() {
    return 2 * radius * Math.PI;
  }
}
