/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kolbe
 */
public class LaunchView {
    boolean SHOW_BORDER = true;
    double MIN_WIDTH = 700,
            MIN_HEIGHT = 500;
    
    BorderPane root;
    private HBox centerH;
    private VBox centerV;
    
    
    private Pane titleCard;
    private Text title;
    private LaunchModel model;
    
    protected LaunchView(LaunchModel model){
        this.model = model;
        initialize();
    }
    
    private void initialize(){
        root = new BorderPane();
        root.setMinSize(MIN_WIDTH, MIN_HEIGHT);
        root.setStyle("-fx-background-color: #292F36;");
        
        titleCard = new Pane();
        titleCard.setStyle("-fx-background-color: #FBFEF9;");
        
        centerH = new HBox();
        centerV = new VBox();
        root.getChildren().add(titleCard);
//        root.setCenter(centerH);
        
           
        root.setCenter(titleCard);
        titleCard.setMinSize(MIN_WIDTH / 3, MIN_HEIGHT / 3);
        
    }
}
