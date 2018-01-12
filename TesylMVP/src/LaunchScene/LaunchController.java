/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaunchScene;

import javafx.scene.control.Button;

/**
 *
 * @author Kolbe
 */
public class LaunchController {

    private Button start;
    private LaunchModel model;

    protected LaunchController(LaunchModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {

    }
}
