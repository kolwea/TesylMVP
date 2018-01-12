/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScene;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author Kolbe
 */
public class MainView {
    
    private BorderPane root;
    private MainModel model;
    
    public MainView(MainModel model){
        this.model = model;
    }
    
    private void setupPane(){
        this.root = new BorderPane();
        
    }
}
