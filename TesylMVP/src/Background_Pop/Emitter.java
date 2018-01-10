/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Pop;

import Tools.OpenSimplexNoise;
import Tools.Vector;
import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class Emitter {

    double radius = 30.0;
    Color color = Color.DARKSLATEBLUE;
    Ellipse body;
    Line cord;
    double radiusX = 20.0, radiusY = 10.0;
    BackgroundPop parent;
    OpenSimplexNoise noise;
    Particle child;
    Vector pos;

    protected Emitter(BackgroundPop par) {
        parent = par;
        noise = new OpenSimplexNoise();
        setupBody();
        emitParticle();
    }

    protected void setPosition(Vector pos) {
        this.pos = pos;
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }

    protected Ellipse getShape() {
        return this.body;
    }

    private void setupBody() {
        this.body = new Ellipse();
        this.body.setRadiusX(radiusX);
        this.body.setRadiusY(radiusY);
        body.setFill(color);
    }

    protected void emitParticle() {
        child = new Particle(this);
        child.setPosition(new Vector(body.getCenterX(), body.getCenterY() - 5));
        parent.getRoot().getChildren().add(child.getShape());
    }

    protected void connect(){
        Node n1 = body, n2 = child.getShape();
        Line line = null;
        if(n1 != null && n2 != null){
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
        cord = line;
        cord.setStrokeWidth(radiusY);
        parent.root.getChildren().add(cord);
    }

    protected void updateParticle(double z) {
        double x = body.getCenterX();
        double y = body.getCenterY();
        double value = noise.eval(x / parent.SCALE / 10, y / parent.SCALE / 10, z);
        double newy = Tools.Functions.map(value, 0, 1, 10, 30);
        Vector pos = new Vector(x, y + newy);
        child.setPosition(pos);
    }

}
