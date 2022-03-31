package component;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * @author Ohad Marmor - 207481524.
 */
public class EndScreen implements Animation {

    // fields:
    private int balls;
    private int score;
    private biuoop.KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param b - the balls in the game.
     * @param s - the score.
     * @param k - a keyboard.
     */
    public EndScreen(int b, int s, biuoop.KeyboardSensor k) {
        this.balls = b;
        this.score = s;
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.balls != 0) {
            d.drawText(50, d.getHeight() / 2
                    , "you win! your score is: " + String.valueOf(this.score), 32);
        } else {
            d.drawText(50, d.getHeight() / 2
                    , "GAME OVER! your score is: " + String.valueOf(this.score), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
