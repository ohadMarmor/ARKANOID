package component;

import biuoop.DrawSurface;
import geometry.Counter;
import interfaces.Sprite;
/**
 * @author Ohad Marmor 207481524.
 */
public class ScoreIndicator implements Sprite {
    private Counter cBlocks;
    private GameEnvironment e;

    /**
     * constructor.
     * @param counterBlocks - the number of blocks.
     * @param environment - the environment of the game.
     */
    public ScoreIndicator(Counter counterBlocks, GameEnvironment environment) {
        this.cBlocks = counterBlocks;
        this.e = environment;

    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 20, "score: " + String.valueOf(this.cBlocks.getValue()), 25);
        if (cBlocks.getValue() == 225) {
            d.drawText(350, 20, "score: 325", 25);
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
