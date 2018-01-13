/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

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

    double MINMAPVAL = -30.0,
            MAXMAPVAL = 30.0,
            NOISE_MAGNITUDE = 1;

    Color color = Color.DARKSLATEBLUE;
    Ellipse body;
    Line cord;
    double radiusX = 10.0, radiusY = 10.0;
    BackgroundFlowfield parent;
    OpenSimplexNoise noise;
    Particle child;
    Vector pos;

    protected Emitter(BackgroundFlowfield par) {
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

    protected Vector getPosition() {
        return new Vector(body.getCenterX(), body.getCenterY());
    }

    private void setupBody() {
        this.body = new Ellipse();
        this.body.setRadiusX(radiusX);
        this.body.setRadiusY(radiusY);
        this.body.setStrokeWidth(1.0);
        this.body.setStroke(Color.web("#000004"));
        body.setFill(Color.web("#A63446"));
    }

    protected void emitParticle() {
        child = new Particle(this);
        child.setPosition(new Vector(body.getCenterX() - 5, body.getCenterY() - 5));
        parent.getRoot().getChildren().add(child.getShape());
    }

    protected void connect() {
        Node n1 = child.getShape(), n2 = this.body;
        Line line = null;
        if (n1 != null && n2 != null) {
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
        cord.setStrokeWidth(3.0);
        cord.setStroke(Color.web("#000004"));
        cord.setFill(Color.web("#23CE6B", 0.5));
        parent.root.getChildren().add(cord);
        cord.toFront();
    }

    protected void connect(Shape otherChild) {
        Node n1 = child.getShape(), n2 = otherChild;
        Line line = null;
        if (n1 != null && n2 != null) {
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
        line.setStrokeWidth(2.0);
        parent.root.getChildren().add(line);
    }

    protected void connect(Emitter fam) {
        Node n1 = this.body, n2 = fam.body;
        Line line = null;
        if (n1 != null && n2 != null) {
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
        cord.setStrokeWidth(2.0);
        parent.root.getChildren().add(cord);
    }

    protected void updateParticle(double z) {
        double x = body.getCenterX() + 10;
        double y = body.getCenterY() - 10;
        double valuey = noise.eval(y / NOISE_MAGNITUDE, z);
        double valuex = noise.eval(x / NOISE_MAGNITUDE, z);
        double valueB = noise.eval(x / NOISE_MAGNITUDE, y / NOISE_MAGNITUDE, z);
        double newy = Tools.Functions.map(valuey, 0, 1, MINMAPVAL, MAXMAPVAL);
        double newx = Tools.Functions.map(valuex, 0, 1, MINMAPVAL, MAXMAPVAL);
        double newB = Tools.Functions.map(valueB, 0, 1, MINMAPVAL, MAXMAPVAL);
        
        double newcolor = noise.eval(x, y, z);
        child.updateColor(newcolor);

        Vector pos;
        switch (parent.mode) {
            case 0:
                pos = new Vector(x + newx, y + newy);
                child.setPosition(pos);
                break;
            case 1:
                pos = new Vector(x + newy, y + newy);
                child.setPosition(pos);
                break;
            case 2:
                pos = new Vector(x + newx, y + newx);
                child.setPosition(pos);
                break;
            case 3:
                pos = new Vector(x, y + newy);
                child.setPosition(pos);
                break;
            case 4:
                pos = new Vector(x + newx, y);
                child.setPosition(pos);
                break;
            case 5:
                pos = new Vector(x + newB, y + newB);
                child.setPosition(pos);
                break;
            default:
                pos = new Vector(x , y );
                child.setPosition(pos);
                break;
        }

    }

}
