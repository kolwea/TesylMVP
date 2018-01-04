/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author Kolbe
 */
public class LaunchView {
    private Pane background;
    private Pane titleCard;
    private Text title;
    private LaunchModel model;
    
    void View(LaunchModel model){
        this.model = model;
    }
    
    private void initialize(){
        
    }
}
