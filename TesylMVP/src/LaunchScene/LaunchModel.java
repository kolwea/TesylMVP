/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Kolbe
 */
public class LaunchModel {
    private final LaunchView view;
    private final LaunchController controller;
    
    public LaunchModel(){
        view = new LaunchView(this);
        controller = new LaunchController(this);
    }
    
    public BorderPane getRoot(){
        return view.root;
    }
}
