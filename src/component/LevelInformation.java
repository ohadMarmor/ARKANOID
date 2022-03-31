package component;

import elements.Block;
import geometry.Velocity;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public interface LevelInformation {
    /**
     * get the number of the balls in this level.
     * @return - number of the balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return list with the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the speed of the paddle in this level.
     * @return - the speed of the paddle in this level.
     */
    int paddleSpeed();

    /**
     * the width of the paddle in this level.
     * @return - the width of the paddle in this level.
     */
    int paddleWidth();

    /**
     * the color of the paddle in this level.
     * @return - the color of the paddle in this level.
     */
    Color paddleColor();

    /**
     * the name of the level. the level name will be displayed at the top of the screen.
     * @return - the name of the level.
     */
    String levelName();

    /**-
     * Returns a sprite with the background of the level.
     * @return - the background of the level.
     */
    Sprite getBackground();

    /**
     * create and returns the draws of each level.
     * @return - the draws of each level.
     */
    List<Sprite> collectionS();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list with the blocks.
     */
    List<Block> blocks();

    /**
     * the number of blocks we need to remove to pass the level.
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * getter.
     * @return the game environment of this level.
     */
    GameEnvironment getEnvironment();
}