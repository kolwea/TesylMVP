/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Kolbe
 */
public class LaunchController {

    private Button start;
    private LaunchModel model;

    protected void Controller(LaunchModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {
        start = new Button();
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
    }
}
