package elements;
import biuoop.DrawSurface;
import component.GameLevel;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Ohad Marmor - 207481524.
 */
public class LevelName implements Sprite {
    private String name;
    private Color color;

    /**
     * constructor.
     * @param n - the name.
     * @param c - the color the name will be written.
     */
    public LevelName(String n, Color c) {
        this.name = n;
        this.color = c;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(600, 20, this.name, 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }

    public String getName() {
        return this.name;
    }
}
