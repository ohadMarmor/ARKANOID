package component;

import elements.Block;
import geometry.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Circle;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Ohad Marmor - 207481524.
 */
public class WideEasy implements LevelInformation {
    private GameEnvironment environment;
    /**
     * constructor.
     * @param e - the game environment.
     */
    public WideEasy(GameEnvironment e) {
        this.environment = e;
    }

    @Override
    public int numberOfBalls() {
        return 8;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i <= this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(110 + i * 10, 6));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public Color paddleColor() {
        return Color.ORANGE;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new geometry.Rectangle(new Point(10, 20), 780, 560), Color.WHITE
                , this.environment);
    }

    @Override
    public List<Sprite> collectionS() {
        List<Sprite> collections = new ArrayList<>();
        for (int i = 0; i <= 300; i++) {
            Sprite line = new Line(new Point(120, 70), new Point(30 + 4 * i, 200), Color.YELLOW);
            collections.add(line);
        }
        Sprite sun1 = new Circle(new Point(120, 70), 50, Color.YELLOW, Color.ORANGE);
        collections.add(sun1);
        Sprite sun = new Circle(new Point(120, 70), 40, Color.YELLOW, Color.YELLOW);
        collections.add(sun);
        return collections;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // create the blocks:
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Color color = Color.WHITE;
            if (i == 0 || i == 1) {
                color = Color.RED;
            } else if (i == 2 || i == 3) {
                color = Color.BLUE;
            } else if (i == 4 || i == 5) {
                color = Color.BLACK;
            } else if (i == 6 || i == 7) {
                color = Color.YELLOW;
            }
            Block b = new Block(new Rectangle(new Point(20 + i * 95, 200), 95, 30), color
                    , this.environment);
            blocks.add(b);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 8;
    }

    @Override
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
