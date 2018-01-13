/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfieldv2;

import Tools.OpenSimplexNoise;
import Tools.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Point {

    protected Color POINT_COLOR = Color.web("#235343");
    protected Color POINT_STROKE_COLOR = Color.web("#124356");
    protected double POINT_RADIUS = 10.0;
    protected double POINT_STROKE_WIDTH = 2.0;

    private final Controller parent;
    private Vector position;
    private Vector origin;
    private Circle body;

    protected Point(Controller par, Vector ori) {
        parent = par;
        origin = ori;
        initializeShape();
    }
    
    protected void setOrigin(Vector pos){
        origin = pos;
    }

    protected Circle getShape() {
        return this.body;
    }

    protected void update() {
        position = parent.getNoisePosition(origin);
        updateBodyPosition();
    }
    
    protected Vector getPosition(){
        return new Vector(position.x, position.y);
    }

    private void initializeShape() {
        body = new Circle();
        body.setFill(POINT_COLOR);
        body.setStroke(POINT_STROKE_COLOR);
        body.setStrokeWidth(POINT_STROKE_WIDTH);
        body.setRadius(POINT_RADIUS);
        body.setCenterX(origin.x);
        body.setCenterY(origin.y);
    }

    private void updateBodyPosition() {
        body.setCenterX(position.x);
        body.setCenterY(position.y);
    }
}
