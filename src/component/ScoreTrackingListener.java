package component;

import elements.Block;
import geometry.Ball;
import geometry.Counter;
import interfaces.HitListener;
/**
 * @author Ohad Marmor 207481524.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter - the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(5);
    }
}