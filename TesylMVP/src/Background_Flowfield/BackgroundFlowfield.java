/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class BackgroundFlowfield {

//    private ArrayList<Emitter> emitters;
    private Emitter[][] emitters;
    private double count;
    int rows, cols;

    protected Pane root;
    protected KeyFrame keyframe;
    protected Timeline timeline;
    protected Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    double SCALE = 165;

    public BackgroundFlowfield() {
        cols = (int) (screensize.width / SCALE);
        rows = (int) (screensize.height / SCALE);
        System.out.println(cols + " " + rows);
        emitters = new Emitter[cols][rows];
        setupPane();
        setupTimeline();
        setupEmitters();
        setupConnections();
    }

    public Pane getRoot() {
        return root;
    }

    private void setupPane() {
        root = new Pane();
        root.setMaxSize(screensize.getWidth(), screensize.getHeight());
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            this.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        count = 0;
        timeline.play();
    }

    private void setupEmitters() {
        System.out.println(screensize.width);
        for (int i = -1; i < cols-1; i++) {
            for (int k = -1; k < rows-1; k++) {
                Emitter hold = new Emitter(this);
                hold.setPosition(new Vector(i * SCALE, k * SCALE));
                hold.emitParticle();
//                hold.connect();
//                hold.getShape().toFront();
//                root.getChildren().add(hold.getShape());
                emitters[i+1][k+1] = hold;
            }
        }
    }

    private void setupConnections() {
        for (int i = 0; i < cols - 1; i++) {
            for (int k = 0; k < rows - 1; k++) {
                Emitter first = emitters[i][k];
                Emitter second = emitters[i + 1][k];
                Emitter third = emitters[i][k + 1];
                first.connect(second.child.getShape());
                second.connect(third.child.getShape());
                third.connect(first.child.getShape());
                first.getShape().toFront();
                second.getShape().toFront();
                third.getShape().toFront();
            }
        }
    }

    private void update() {
        for (int i = 0; i < cols; i++) {
            for (int k = 0; k < rows; k++) {
                Emitter curr = emitters[i][k];
                curr.updateParticle(count);
                count += 0.000008;
            }
        }
    }
}
