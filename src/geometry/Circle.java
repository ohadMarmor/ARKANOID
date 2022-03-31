package geometry;

import biuoop.DrawSurface;
import component.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Ohad Marmor - 207481524.
 */
public class Circle implements Sprite {
    // fields:
    private Point center;
    private int radius;
    private Color color;
    private Color fill = Color.cyan;

    /**
     * constructor.
     * @param c - the center of the circle.
     * @param r - the radius of the circle.
     * @param co - the color of the circle.
     */
    public Circle(Point c, int r, Color co) {
        this.center = c;
        this.radius = r;
        this.color = co;
    }
    /**
     * constructor.
     * @param c - the center of the circle.
     * @param r - the radius of the circle.
     * @param co - the color of the circle.
     * @param f - the color inside the circle.
     */
    public Circle(Point c, int r, Color co, Color f) {
        this.center = c;
        this.radius = r;
        this.color = co;
        this.fill = f;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int)this.center.getX(), (int)this.center.getY(), this.radius);
        if (!this.fill.equals(Color.cyan)) {
            d.setColor(this.fill);
            d.fillCircle((int)this.center.getX(), (int)this.center.getY(), this.radius);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
