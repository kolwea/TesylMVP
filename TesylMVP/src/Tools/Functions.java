/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class Functions {

    public Functions() {

    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double a = Math.pow((x2 - x1), 2);
        double b = Math.pow((y2 - y1), 2);

        return Math.sqrt(a + b);
    }

    //Helper Functions//////////////////////////////////////////////////////////
    public static Line connect(Shape one, Shape two) {
        Node n1 = one, n2 = two;
        Line line = null;
        if(one != null && two != null){
            line = new Line();
            line.setStrokeWidth(1);
            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n1.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, n1.boundsInParentProperty()));
            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n1.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, n1.boundsInParentProperty()));
            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, n2.boundsInParentProperty()));
            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, n2.boundsInParentProperty()));
        }
        return line;
    }

    public static double map(double value, double inMin, double inMax, double outMin, double outMax) {
        double done = outMin + ((outMax - outMin) / (inMax - inMin)) * (value - inMin);
        return done;
    }
}
