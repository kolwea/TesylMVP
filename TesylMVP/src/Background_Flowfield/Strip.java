/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

import Tools.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Kolbe
 */
public class Strip {
    double STRIP_STROKE_WIDTH = 3.0;
    Color STRIP_FILL = Color.web("#292F36",0.5);
    Color STRIP_STROKE_COLOR = Color.BLACK;

    private Particle pOne, pTwo, pThree;
    private Vector posOne, posTwo, posThree;
    private Line lineOne, lineTwo, lineThree;
    private Polygon body;

    protected Strip(Particle one, Particle two, Particle three) {
        pOne = one;
        pTwo = two;
        pThree = three;
        updatePositions();
        initialize();
    }

    protected void update() {
        updatePositions();
        body.getPoints().setAll(posOne.x, posOne.y, posTwo.x, posTwo.y, posThree.x, posThree.y);
    }
    
    protected Polygon getBody(){
        return body;
    }

    private void initialize() {
        body = new Polygon(posOne.x, posOne.y, posTwo.x, posTwo.y, posThree.x, posThree.y);
        body.setFill(STRIP_FILL);
        body.setStroke(STRIP_STROKE_COLOR);
        body.setStrokeWidth(STRIP_STROKE_WIDTH);
    }

//    protected Line connect(Shape one, Shape two) {
//        Node n1 = one, n2 = two;
//        Line line = null;
//        if (n1 != null && n2 != null) {
//            line = new Line();
//            line.setStrokeWidth(1);
//            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
//                Bounds b = n1.getBoundsInParent();
//                return b.getMinX() + b.getWidth() / 2;
//            }, n1.boundsInParentProperty()));
//            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
//                Bounds b = n1.getBoundsInParent();
//                return b.getMinY() + b.getHeight() / 2;
//            }, n1.boundsInParentProperty()));
//            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
//                Bounds b = n2.getBoundsInParent();
//                return b.getMinX() + b.getWidth() / 2;
//            }, n2.boundsInParentProperty()));
//            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
//                Bounds b = n2.getBoundsInParent();
//                return b.getMinY() + b.getHeight() / 2;
//            }, n2.boundsInParentProperty()));
//        }
//        cord.setStrokeWidth(2.0);
//    }

    private void updatePositions() {
        posOne = pOne.getPosition();
        posTwo = pTwo.getPosition();
        posThree = pThree.getPosition();
    }

}
