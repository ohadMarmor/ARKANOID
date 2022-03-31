package interfaces;

import elements.Block;
import geometry.Ball;
/**
 * @author Ohad Marmor 207481524.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that got hit.
     * @param hitter - the ball that hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
