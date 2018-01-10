/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Background_Pop;

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
public class BackgroundPop {
     double SCALE = 80.0;
    private ArrayList<Emitter> emitters;
    private double count;
    
    protected Pane root;
    protected KeyFrame keyframe;
    protected Timeline timeline;
    protected Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public BackgroundPop(){
        setupPane();
        setupTimeline();
        setupEmitters();
       
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private void setupPane(){
        root = new Pane();
        root.setMaxSize(screensize.getWidth(), screensize.getHeight());
    }
    
    private void setupTimeline(){
        keyframe = new KeyFrame(Duration.millis(10),(ActionEvent event)->{
            this.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        count = 0;
        timeline.play();
    }
    
    private void setupEmitters(){
        emitters = new ArrayList();
        System.out.println(screensize.width);
        for(int i = 0; i*SCALE < (int)screensize.width; i++){
           for(int k = 0; k*SCALE < (int)screensize.height; k++){
               Emitter hold = new Emitter(this);
               hold.setPosition(new Vector(i*SCALE,k*SCALE));
               hold.emitParticle();
               hold.connect();
               root.getChildren().add(hold.getShape());
               emitters.add(hold);
           } 
        }
    }
    
    private void update(){
        for(Emitter test : emitters){
            count+=0.000001;
            test.updateParticle(count);
        }
    }
}
