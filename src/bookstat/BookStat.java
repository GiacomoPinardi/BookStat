/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstat;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author giacomo
 */
public class BookStat {

    public static void main(String[] args) {
        
        GraphicInterface GUI = new GraphicInterface();
        GUI.show();
        
        
        //launch(args);
        
    }
     
    public void start(Stage stage) throws FileNotFoundException {
        
        String fileName = "WarAndPeace";
        stage.setTitle(fileName + " Book Stat");
        
        Group root = new Group();                
        Canvas canvas = new Canvas(640, 480);        
        GraphicsContext gc = canvas.getGraphicsContext2D();        
        Drawer d = new Drawer(gc, (canvas.getHeight()/100)*6);
        //  
        BookReader br = new BookReader();
        
        br.read("data/" + fileName.toLowerCase() + ".txt");
        
        double v[] = br.getPercentage();
        //
        d.drawAxis(Color.RED, 2);
        d.drawBars(v);
        
        root.getChildren().add(canvas);
        
        stage.setScene(new Scene(root));
        
        stage.show();
    }
    
}
