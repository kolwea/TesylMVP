/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

import Tools.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Kolbe
 */
public class Particle {
    private Emitter parent;
    private Ellipse body;
    private Vector pos;
    
    protected Particle(Emitter par){
        parent = par;
        setupBody();
    }
    
    protected void setPosition(Vector pos){
        this.pos = pos;
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }
    
    protected Ellipse getShape(){
        return this.body;
    }
    
    private void setupBody(){
        body = new Ellipse();
        body.setFill(Color.BLACK);
        body.setRadiusX(parent.radiusX);
        body.setRadiusY(parent.radiusY);        
    }
}
