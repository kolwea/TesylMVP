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
        
        return Math.sqrt(a+b);
    }
    
        //Helper Functions//////////////////////////////////////////////////////////
    protected void connect(Pane pane, Circle one, Circle two) {
        Node n1 = one, n2 = two;
        if (pane == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = pane;
            Line line = new Line();
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
            line.strokeWidthProperty().bind(one.radiusProperty().add(two.radiusProperty()).divide(10));
            parent.getChildren().add(line);
            n1.toFront();
            n2.toFront();
        }
    }
}
