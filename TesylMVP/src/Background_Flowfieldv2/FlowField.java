/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfieldv2;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;

/**
 *
 * @author Kolbe
 */
public class FlowField {
    private int ROW = 70, COLS = 50;
    
    private Pane root;
    private final Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    private Controller controller;
    
    public FlowField(){
        initialize();
    }
    
    public Pane getRoot(){
        return this.root;
    }
    
    private void initialize(){
        controller = new Controller(this);
        setupPane();
        setupPoints();
    }
    
    private void setupPane(){
        root = new Pane();
        root.setMinSize(600,450);
        root.setPrefSize(600, 400);
        root.setMaxSize(window.width, window.height);
    }
        
    private void setupPoints(){
        double xMult = window.getWidth()/ROW;
        double yMult = window.getHeight()/COLS;
        
        for(int i = 0; i < COLS ; i++){
            for(int k = 0; k < ROW; k++){
                Vector pos = new Vector(xMult*i, yMult * k);
                System.out.println(pos.x + " " + pos.y);
                controller.addPoint(pos);
            }
        }
    }
    
}
