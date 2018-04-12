package KiwiIsland;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Timer;


/**
* The Animation class provides the functionality of the Sprites
* to display animation during the game.
* 
* @author Team Taniwha
* @version 1.0
* 
*/
public class Animation {
    public static int DEFAULT_UPDATE_TIME = 40;
    
    private int updateTime;
    private Timer timer;
    private int   curFrameCount;
    
    // Frames that make up an animation sequence.
    private ArrayList<BufferedImage> frames;
    
    
    /**
     * 
     * @param frames 
     */
    public Animation(BufferedImage[] frames, int updateTime, boolean runOnce) {
        this.updateTime = updateTime;
        this.frames = new ArrayList<>(Arrays.asList(frames));
        initTimer(runOnce);
    }
    
    /**
     * 
     */
    public void initTimer(boolean runOnce) {
        // Start a timer that updates every 40 miliseconds (0.04 seconds).
        // Every time the timer updates it changes the current frame 
        // so that callers can render the latest frame as it changes, simulating
        // an animation.
        this.timer = new Timer(updateTime, (ActionEvent ae) -> {
            curFrameCount = (curFrameCount + 1) % this.frames.size();
         
            if (runOnce && curFrameCount == this.frames.size()-1) {
                this.stop();
            }
        });
    }
    
    /**
     * 
     * @param newTime 
     */
    public void setUpdateTime(int newTime) {
        this.updateTime = newTime;
    }
    
    /**
     * 
     * @param frame 
     */
    public void addFrame(BufferedImage frame) {
        this.frames.add(frame);
    }
    
    public int getTotalTime() {
        return this.updateTime*this.frames.size();
    }
    
    /**
     * 
     */
    public void start() {
        this.timer.start();
    }
    
    /**
     * 
     */
    public void stop() {
        // Stop the timer and reset the current frame.
        this.curFrameCount = 0;
        this.timer.stop();
    }
    
    /**
     * 
     * @return 
     */
    public BufferedImage getCurFrame() {
        return this.frames.get(curFrameCount);
    }
}
