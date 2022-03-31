package component;
import elements.Block;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public class Green implements LevelInformation {
    private GameEnvironment environment;
    /**
     * constructor.
     * @param e - the game environment.
     */
    public Green(GameEnvironment e) {
        this.environment = e;
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(110 + i * 10, 8 - i * 2));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Color paddleColor() {
        return Color.RED;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new geometry.Rectangle(new geometry.Point(10, 20), 780, 560), Color.GREEN
                , this.environment);
    }

    @Override
    public List<Sprite> collectionS() {
        List<Sprite> collections = new ArrayList<>();
        Sprite base = new Block(new Rectangle(new Point(45, 400), 131, 185), Color.BLACK);
        collections.add(base);
        Sprite second = new Block(new Rectangle(new Point(88, 340), 40, 60), Color.DARK_GRAY);
        collections.add(second);
        Sprite third = new Block(new Rectangle(new Point(98, 200), 20, 140), Color.GRAY);
        collections.add(third);
        Sprite c1 = new Circle(new Point(108, 188), 12, Color.ORANGE, Color.ORANGE);
        collections.add(c1);
        Sprite c2 = new Circle(new Point(108, 188), 7, Color.RED, Color.RED);
        collections.add(c2);
        Sprite c3 = new Circle(new Point(108, 188), 3, Color.WHITE, Color.WHITE);
        collections.add(c3);
        double x = 55, y = 415;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                Sprite window = new Block(new Rectangle(new Point(x + j * 25, y), 12, 20), Color.WHITE);
                collections.add(window);
            }
            y += 38;
        }
        return collections;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.DARK_GRAY, Color.YELLOW, Color.PINK};
        double x, y = 80;
        double widthBlock = 60, heightBlock = 20;
        for (int i = 0; i < 6; i++) {
            x = 720;
            for (int j = i; j < 10; j++) {
                Point start = new Point(x, y);
                Block block = new Block(new Rectangle(start, widthBlock, heightBlock), colors[i], this.environment);
                blocks.add(block);
                x -= widthBlock;
            }
            y += heightBlock;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 45;
    }

    @Override
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
