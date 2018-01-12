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
    
    protected void updateColor(double val){
        int color = (int) Tools.Functions.map(val, 0, 1, 150, 206);
        color = Math.abs(color%255);
        body.setFill(Color.rgb(35, color, 107));
    }
    
    protected Vector getPosition(){
        return new Vector(body.getCenterX(),body.getCenterY());
    }
    
    protected Ellipse getShape(){
        return this.body;
    }
    
    private void setupBody(){
        body = new Ellipse();
        body.setFill(Color.web("#292F36",0.2));
        body.setStroke(Color.web("#000000"));
        body.setStrokeWidth(1.0);
        body.setRadiusX(parent.radiusX);
        body.setRadiusY(parent.radiusY);        
    }
    
    
}
