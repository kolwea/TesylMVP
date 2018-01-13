/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfieldv2;

import Tools.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Kolbe
 */
public class Cell {
    private final Color SHAPE_FILL = Color.YELLOW;
    private final Color SHAPE_STROKE = Color.BLUE;
    private final double SHAPE_STROKE_WIDTH = 3.0;
    
    private Controller parent;
    private Point[] points;
    private Polygon body;
    
    protected Cell(Point one, Point two,Point three){
        points = new Point[]{one,two,three};
        initalizeShape();
    }
    
    protected Polygon getShape(){
        return body;
    }
    
    protected void update(){
        Vector one = points[0].getPosition(),
               two = points[1].getPosition(),
               three = points[2].getPosition();
        body.getPoints().setAll(one.x,one.y,two.x,two.y,three.x,three.y);
    }
    
    private void initalizeShape(){
        body = new Polygon();
        body.setStrokeWidth(SHAPE_STROKE_WIDTH);
        body.setStroke(SHAPE_STROKE);
        body.setFill(SHAPE_FILL);
    }
    
    
}
