package component;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;

import java.awt.Color;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
/**
 * @author Ohad Marmor - 207481524.
 */
public class CountdownAnimation implements Animation {
    // fields:
    private double seconds;
    private double count;
    private SpriteCollection screen;

    /**
     * constructor.
     * @param numOfSeconds - double.
     * @param countFrom - integer.
     * @param gameScreen - the sprites that the class needs to be aware of.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.count = countFrom;
        this.screen = gameScreen;
    }

    /**
     * setter.
     */
    private void setCount() {
        this.count--;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        biuoop.Sleeper sleeper = new Sleeper();
        this.screen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(300, 350, String.valueOf(this.count), 200);
        setCount();
        sleeper.sleepFor((long) this.seconds);
    }
    @Override
    public boolean shouldStop() {
        return this.count == -1;
    }
}
