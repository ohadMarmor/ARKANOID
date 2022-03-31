package component;

import elements.Block;
import geometry.Ball;
import geometry.Counter;
import interfaces.HitListener;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
/**
 * @author Ohad Marmor - 207481524.
 */
public class BlockRemover implements HitListener {
    private GameLevel g;
    private Counter remainingBlocks;
    /**
     * constructor.
     * @param gameLevel - the game with the remover.
     * @param removedBlocks - the number of blocks left.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.g = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.g);
        this.remainingBlocks.decrease(1);
    }
}
