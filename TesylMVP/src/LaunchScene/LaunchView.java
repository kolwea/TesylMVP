/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import Background_Flowfield.BackgroundFlowfield;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    private BorderPane titleCard;
    private Text title;
    private LaunchModel model;

    protected LaunchView(LaunchModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {
        root = new BorderPane();
        root.setMinSize(MIN_WIDTH, MIN_HEIGHT);
        root.setStyle("-fx-background-color: #D6D8D4;");

//        titleCard = new Pane();
//        titleCard.setStyle("-fx-background-color: #FBFEF9;");
        setupBackground();
        setupTitle();
    }

    private void setupBackground() {
        BackgroundFlowfield flowfield = new BackgroundFlowfield();
        root.getChildren().add(flowfield.getRoot());
        flowfield.getRoot().toFront();
    }
    
    private void setupTitle(){
        titleCard = new BorderPane();
        titleCard.setStyle("-fx-background-color: #f3f3f3;"
                + "-fx-border-color: black;");
        titleCard.setMinSize(MIN_WIDTH/3,MIN_HEIGHT/3);
        titleCard.setMaxSize(MIN_WIDTH/1.5, MIN_HEIGHT/1.5);
        titleCard.setPrefSize(MIN_WIDTH-200, MIN_HEIGHT-400);
        root.setCenter(titleCard);
        
        Text title = new Text("Tesyl");
        title.setStyle("-fx-font-family:'Shrikhand'; -fx-font-size: 80;");
        titleCard.setTop(title);
    }
}
