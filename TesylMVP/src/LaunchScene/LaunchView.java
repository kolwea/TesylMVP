/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import Background_Flowfield.BackgroundFlowfield;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Kolbe
 */
public class LaunchView {

    boolean SHOW_BORDER = true;
    double MIN_WIDTH = 700,
            MIN_HEIGHT = 500;

    BorderPane root;
    Button changeMode;

    private BorderPane titleCard;
    private BackgroundFlowfield background;
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
        background = new BackgroundFlowfield();
        root.getChildren().add(background.getRoot());
    }

    private void setupTitle() {
        titleCard = new BorderPane();
        titleCard.setStyle("-fx-background-color: #f3f3f3;"
                + "-fx-border-color: black;");
        titleCard.setMinSize(MIN_WIDTH / 3, MIN_HEIGHT / 3);
        titleCard.setMaxSize(MIN_WIDTH / 1.5, MIN_HEIGHT / 1.5);
        titleCard.setPrefSize(MIN_WIDTH - 200, MIN_HEIGHT - 400);
        root.setCenter(titleCard);

        changeMode = new Button();
        changeMode.setText("Change Background");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                background.changeStyle();
                background.setLines();
            }
        });
        titleCard.setBottom(changeMode);
        Text title = new Text("Tesyl");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setStyle("-fx-font-family:'Shrikhand'; -fx-font-size: 80;");
        titleCard.setTop(title);
    }
}
