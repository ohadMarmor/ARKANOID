package component;

import elements.Block;
import geometry.Line;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public class FinalFour implements LevelInformation {
    private GameEnvironment environment;
    /**
     * constructor.
     * @param e - the game environment.
     */
    public FinalFour(GameEnvironment e) {
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
        return Color.ORANGE;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new geometry.Rectangle(new geometry.Point(10, 20), 780, 560), Color.CYAN
                , this.environment);
    }

    @Override
    public List<Sprite> collectionS() {
        List<Sprite> collections = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            Sprite l = new Line(new Point(80 + i * 10, 390), new Point(35 + i * 10, 800), Color.WHITE);
            collections.add(l);
        }
        Sprite c1 = new Circle(new Point(80, 360), 20, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        collections.add(c1);
        Sprite c2 = new Circle(new Point(105, 370), 30, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        collections.add(c2);
        Sprite c3 = new Circle(new Point(125, 350), 38, Color.GRAY, Color.GRAY);
        collections.add(c3);
        Sprite c4 = new Circle(new Point(175, 360), 45, Color.DARK_GRAY, Color.DARK_GRAY);
        collections.add(c4);
        Sprite c5 = new Circle(new Point(140, 380), 30, Color.DARK_GRAY, Color.DARK_GRAY);
        collections.add(c5);
        for (int i = 0; i < 13; i++) {
            Sprite l = new Line(new Point(630 + i * 10, 420), new Point(585 + i * 10, 800), Color.WHITE);
            collections.add(l);
        }
        Sprite c6 = new Circle(new Point(630, 390), 20, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        collections.add(c6);
        Sprite c7 = new Circle(new Point(655, 400), 30, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        collections.add(c7);
        Sprite c8 = new Circle(new Point(675, 380), 38, Color.GRAY, Color.GRAY);
        collections.add(c8);
        Sprite c9 = new Circle(new Point(725, 390), 45, Color.DARK_GRAY, Color.DARK_GRAY);
        collections.add(c9);
        Sprite c10 = new Circle(new Point(690, 410), 30, Color.DARK_GRAY, Color.DARK_GRAY);
        collections.add(c10);
        return collections;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.DARK_GRAY, Color.YELLOW, Color.PINK};
        double x, y = 80;
        double widthBlock = 63, heightBlock = 20;
        // create the blocks. this is for the lines.
        for (int i = 0; i < 6; i++) {
            x = 715;
            // create the blocks. this is for columns.
            for (int j = 0; j < 12; j++) {
                geometry.Point start = new geometry.Point(x, y);
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
        return 72;
    }

    @Override
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
