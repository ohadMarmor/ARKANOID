package component;

import elements.Block;
import geometry.Ball;
import geometry.Counter;
import interfaces.HitListener;

/**
 * @author Ohad Marmor - 207481524.
 */
public class BallRemover implements HitListener {
    private GameLevel g;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel - the game with the remover.
     * @param removedBalls - the number of balls left.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.g = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.g);
        this.remainingBalls.decrease(1);
    }
}
