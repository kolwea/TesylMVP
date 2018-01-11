/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylmvp;

import Background_Flowfield.BackgroundFlowfield;
import LaunchScene.LaunchModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class TesylMVP extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        LaunchModel launch = new LaunchModel();
        Pane root = launch.getRoot();
        
//        BackgroundFlowfield testPop = new BackgroundFlowfield();
//        Pane root = testPop.getRoot();
        Scene scene = new Scene(root, 800 ,600);
        
        primaryStage.setTitle("Tesyl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
