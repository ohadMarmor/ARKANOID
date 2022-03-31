package component;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ohad Marmor 207481524
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * defining the sprites list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * add a new sprite to the list.
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * remove a new sprite to the list.
     * @param s - the new sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    // call timePassed() on all sprites.
    /**
     * this method activates timePassed for every sprite in the list.
     */
    public void notifyAllTimePassed() {
        List<Sprite> reserve = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : reserve) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * in this method we use the drawOn method for every sprite in the list.
     * @param d the draw surface we draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
