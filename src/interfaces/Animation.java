package interfaces;
import biuoop.DrawSurface;

/**
 * @author Ohad Marmor - 207481524.
 */
public interface Animation {
    /**
     * do one frame of the animation (will be used inside a loop).
     * @param d - the board we draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * tells when to stop running the animation.
     * @return true or false.
     */
    boolean shouldStop();
}
