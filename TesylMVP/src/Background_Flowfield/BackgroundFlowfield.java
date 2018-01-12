/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Flowfield;

import Tools.Vector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
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
    double SAFE_PADDING = 300;
    boolean SHOW_EMITTER = true,
            CONNECT_EMITTERS = false,
            SHOW_LINES = false;

    protected Pane root;
    protected KeyFrame keyframe;
    protected Timeline timeline;
    protected ArrayList<Strip> cells;
    protected Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int mode;
    double SCALEX = 160, SCALEY = 100;

    public BackgroundFlowfield() {
        cols = (int) (screensize.width / SCALEX);
        rows = (int) (screensize.height / SCALEY);
        System.out.println(cols + " " + rows);
        emitters = new Emitter[cols][rows];
        setupPane();
        setupTimeline();
        setupEmitters();
        setupConnections();
        mode = 0;
        FieldController test = new FieldController(this);
    }

    public Pane getRoot() {
        return root;
    }

    public void changeStyle() {
        mode++;
        if (mode > 9) {
            mode = 0;
        }
    }

    protected ArrayList<Emitter> getSafePoints() {
        Vector TL = new Vector(0, 0),
                TR = new Vector(screensize.getWidth(), 0),
                BL = new Vector(0, screensize.getHeight()),
                BR = new Vector(screensize.getWidth(), screensize.getHeight());
        
        ArrayList<Emitter> done = new ArrayList();
        
        for (int i = 0; i < cols; i++) {
            for (int k = 0; k < rows; k++) {
                Emitter curr = emitters[i][k];
                Vector pos = curr.getPosition();
                if (pos.distance(TL) >= SAFE_PADDING) {
                    if (pos.distance(TR) >= SAFE_PADDING) {
                        if (pos.distance(BL) >= SAFE_PADDING) {
                            if (pos.distance(BR) >= SAFE_PADDING) {
                                done.add(curr);
                            }
                        }
                    }
                }
            }
        }
        return done;
    }

    private void setupPane() {
        root = new Pane();
        root.setMaxSize(screensize.getWidth(), screensize.getHeight());
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(1), (ActionEvent event) -> {
            this.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        count = 0;
        timeline.play();
    }

    private void setupEmitters() {
        System.out.println(screensize.width);
        for (int i = -1; i < cols - 1; i++) {
            for (int k = -1; k < rows - 1; k++) {
                Emitter hold = new Emitter(this);
                hold.setPosition(new Vector(i * SCALEX, k * SCALEY));
                hold.emitParticle();
                if (SHOW_EMITTER == true) {
                    hold.connect();
                    hold.getShape().toFront();
                    root.getChildren().add(hold.getShape());
                }
                emitters[i + 1][k + 1] = hold;
            }
        }
    }

    private void setupConnections() {
        cells = new ArrayList();
        for (int i = 0; i < cols - 1; i++) {
            for (int k = 0; k < rows - 1; k++) {
                Emitter first = emitters[i][k];
                Emitter second = emitters[i + 1][k];
                Emitter third = emitters[i][k + 1];
                Strip hold = new Strip(first.child, second.child, third.child);
                cells.add(hold);
                root.getChildren().add(hold.getBody());
                first.getShape().toFront();
                second.getShape().toFront();
                third.getShape().toFront();
            }
        }
        if (SHOW_LINES == true) {
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
        if (CONNECT_EMITTERS == true) {
            for (int i = 0; i < cols - 1; i++) {
                for (int k = 0; k < rows - 1; k++) {
                    Emitter first = emitters[i][k];
                    Emitter second = emitters[i + 1][k];
                    Emitter third = emitters[i][k + 1];
                    first.connect(second);
                    second.connect(third);
                    third.connect(first);
                    first.getShape().toFront();
                    second.getShape().toFront();
                    third.getShape().toFront();
                }
            }
        }
    }

    private void setupUpperShape() {
        if (CONNECT_EMITTERS == true) {
            for (int i = 0; i < cols - 1; i++) {
                for (int k = 0; k < rows - 1; k++) {
                    Emitter first = emitters[i][k];
                    Emitter second = emitters[i + 1][k];
                    Emitter third = emitters[i][k + 1];

                    first.connect(second);
                    second.connect(third);
                    third.connect(first);
                    first.getShape().toFront();
                    second.getShape().toFront();
                    third.getShape().toFront();
                }
            }
        }
    }

    private void update() {
        for (int i = 0; i < cols; i++) {
            for (int k = 0; k < rows; k++) {
                Emitter curr = emitters[i][k];
                curr.updateParticle(count);
                count += 0.0000005;
            }
        }
        for (Strip curr : cells) {
            curr.update();
        }
    }
}
