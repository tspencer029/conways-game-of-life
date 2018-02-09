package gui;


import java.util.LinkedList;

import gameoflife.Cell;
import gameoflife.World;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import gameoflife.Cell;
import gameoflife.World;

/**
 * 
 * @author Tyler Spencer, Gurtej Singh
 * @version 0.1
 * 
 * 
 *Builds a user interface to display cells  
 *Has a start button, pause and reset/stop button
 *
 */
public class GUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn1 = new Button();
        btn1.setText("Play");
        Button btn2 = new Button();
        btn2.setText("Pause"); 
        Button btn3 = new Button();
        btn3.setText("Stop/Reset");
        
        Group root = new Group();
       
        
        Pane pane = new Pane();
        pane.setPadding(new Insets(10));
        
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(5));
        hbox.getChildren().addAll(btn1, btn2, btn3);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(pane);
        
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conways game of Life");
        primaryStage.show();
        
       
        
        World world = new World(800, 600);
        for (LinkedList<Cell> column : world.getMap()){
        	
        	for (Cell cell : column) {
       
        		pane.getChildren().add(cell);
        	}
        	
        }
        
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				world.updateWorld();
			}
        });
        
        
        Timeline timeline  = new Timeline(); 
        timeline.setCycleCount(Timeline.INDEFINITE); 
        timeline.setAutoReverse(false); 
        timeline.getKeyFrames().add(frame); 


        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.play();
            }
            
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.pause();
            }
            
        });
        
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.stop();
            }
            
        });
    
    }
    public static void main(String[] args) {
        launch(args);
    }
}