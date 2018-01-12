/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Kolbe
 */
public class FieldController {
    private BackgroundFlowfield field;
    private Emitter[][] emitters;
    private ArrayList<Strip> cells;
    private final Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
    
    protected FieldController(BackgroundFlowfield field){
        this.field = field;
        updateSafeArea();
    }
    
    private void updateSafeArea(){
        double safeX = window.getWidth();
        double safeY = window.getHeight();
        for(Emitter curr : field.getSafePoints()){
            curr.body.setFill(Color.BLACK);
        }
    }
}
