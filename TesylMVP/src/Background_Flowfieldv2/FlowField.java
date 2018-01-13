/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfieldv2;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 * @author Kolbe
 */
public class FlowField {

    final int ROWS = 20;
    final int COLS = 10;

    private Pane root;
    private final Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    private Controller controller;

    public FlowField() {
        initialize();
    }

    public Pane getRoot() {
        return this.root;
    }

    private void initialize() {
        controller = new Controller(this);
        setupPane();
        setupPoints();
        setupShapes();
    }

    private void setupPane() {
        root = new Pane();
        root.setMinSize(600, 450);
        root.setPrefSize(600, 400);
        root.setMaxSize(window.width, window.height);
    }

    private void setupPoints() {
        double xMult = (window.getWidth() + 50) / ROWS;
        double yMult = (window.getHeight() + 50) / COLS;

        for (int i = -1; i < COLS-1; i++) {
            for (int k = -1; k < ROWS-1; k++) {
                Vector pos = new Vector(xMult * i, yMult * k);
                controller.addPoint(pos);
            }
        }
    }

    private void setupShapes() {
        controller.setupCells();
        ArrayList<Cell> cells = controller.getCells();
        for(Cell curr : cells){
            root.getChildren().add(curr.getShape());
        }
    }

}
