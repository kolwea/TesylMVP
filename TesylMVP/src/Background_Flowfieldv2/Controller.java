/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfieldv2;

import Tools.OpenSimplexNoise;
import Tools.Vector;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Controller {

    int MAX_MAP_VALUE = 40,
            MIN_MAP_VALUE = -40;
    double COUNT_INCREMENT = 0.006;

    private final OpenSimplexNoise noise = new OpenSimplexNoise();
    private ArrayList<Point> points;
    private ArrayList<Cell> cells;
    private int noiseMode;
    private double count;
    private FlowField parent;
    private Timeline timeline;
    private KeyFrame keyframe;

    protected Controller(FlowField par) {
        parent = par;
        initialize();
    }

    private void initialize() {
        count = 0;
        noiseMode = 5;
        setupTimeline();
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    protected ArrayList<Point> getPoints() {
        return points;
    }

    protected ArrayList<Cell> getCells() {
        return cells;
    }

    protected Vector getNoisePosition(Vector pos) {
//        double noiseX = noise.eval(pos.x, count);
//        double noiseY = noise.eval(pos.y, count);
        double noiseB = noise.eval(pos.x, pos.y, count);

//        double valX = Tools.Functions.map(noiseX, -1, 1, MIN_MAP_VALUE, MAX_MAP_VALUE);
//        double valY = Tools.Functions.map(noiseY, -1, 1, MIN_MAP_VALUE, MAX_MAP_VALUE);
        double valX = 0;
        double valY = 0;
        double valB = Tools.Functions.map(noiseB, -1, 1, MIN_MAP_VALUE, MAX_MAP_VALUE);

        Vector done = null;
        switch (noiseMode) {
            case 0:
                done = new Vector(pos.x + valX, pos.y);
                break;
            case 1:
                done = new Vector(pos.x, pos.y + valY);
                break;
            case 2:
                done = new Vector(pos.x + valX, pos.y + valY);
                break;
            case 3:
                done = new Vector(pos.x + valB, pos.y);
                break;
            case 4:
                done = new Vector(pos.x, pos.y + valB);
                break;
            case 5:
                done = new Vector(pos.x + valB, pos.y + valB);
                break;
            default:
                done = new Vector(pos.x, pos.y);
                break;
        }
        return done;
    }

    protected void addPoint(Vector origin) {
        if (points == null) {
            points = new ArrayList();
        }
        Point hold = new Point(this, origin);
        points.add(hold);
        parent.getRoot().getChildren().add(hold.getShape());
        System.out.println(points.size());
    }

    protected void update() {
        count += COUNT_INCREMENT;
        for (Point curr : points) {
            curr.update();
        }
        if (cells != null) {
            for (Cell curr : cells) {
                curr.update();
            }
        }
    }

    protected void setupCells() {
        int p = 0;
        Point[][] array = new Point[parent.COLS][parent.ROWS];
        for (int i = 0; i < parent.COLS; i++) {
            for (int k = 0; k < parent.ROWS; k++) {
                array[i][k] = points.get(p);
                p++;
            }
        }
        cells = new ArrayList<>();
        for (int i = 0; i < parent.COLS - 1; i++) {
            for (int k = 0; k < parent.ROWS - 1; k++) {
                Point one = array[i][k];
                Point two = array[i + 1][k];
                Point three = array[i][k + 1];
                Point four = array[i + 1][k + 1];
                Cell top = new Cell(one, two, three);
                Cell bot = new Cell(three, four, two);
                cells.add(top);
                cells.add(bot);
            }
        }
    }
}
