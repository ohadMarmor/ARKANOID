package interfaces;

import biuoop.DrawSurface;
import component.GameLevel;

/**
 * @author Ohad Marmor 207481524.
 */
public interface Sprite {
    // draw the sprite to the screen.
    /**
     * draw the interfaces.Sprite. each class implements this method differently.
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed.

    /**
     * each class implements differently.
     */
    void timePassed();
    // add to the game:

    /**
     * activating the sprite by calling spcific methods, depends on the class.
     * @param g - the game we add this sprite to.
     */
    void addToGame(GameLevel g);
}
