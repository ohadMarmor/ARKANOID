package component;

import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

/**
 * @author Ohad Marmor - 207481524.
 */
public class AnimationRunner {
    // fields:
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * constructor.
     * @param g - gui.
     * @param frame - integer.
     */
    public AnimationRunner(GUI g, int frame) {
        this.gui = g;
        this.framesPerSecond = frame;
    }

    /**
     * this method runs the animation it gets using the class' gui and according to the classw frame.
     * @param animation - an implemented interface that the method receives.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = this.framesPerSecond;
        // runs until shouldStop (probably a specific key is entered.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}