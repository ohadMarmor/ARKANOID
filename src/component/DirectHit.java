package component;

import elements.Block;
import elements.LevelName;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import geometry.Circle;
import geometry.Line;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public class DirectHit implements LevelInformation {
    private GameEnvironment environment;
    /**
     * constructor.
     * @param e - the game environment.
     */
    public DirectHit(GameEnvironment e) {
        this.environment = e;
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public Color paddleColor() {
        return Color.WHITE;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(10, 20), 780, 560), Color.BLACK, this.environment);
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block(new Rectangle(new Point(375, 150), 80, 80), Color.RED, this.environment);
        List<Block> blocks = new ArrayList<>();
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Sprite> collectionS() {
        List<Sprite> collection = new ArrayList<>();
        Sprite c1 = new Circle(new Point(415, 190), 70, Color.RED);
        collection.add(c1);
        Sprite c2 = new Circle(new Point(415, 190), 100, Color.RED);
        collection.add(c2);
        Sprite c3 = new Circle(new Point(415, 190), 130, Color.RED);
        collection.add(c3);
        Sprite l1 = new Line(new Point(375, 190), new Point(285, 190), Color.RED);
        collection.add(l1);
        Sprite l2 = new Line(new Point(455, 190), new Point(545, 190), Color.RED);
        collection.add(l2);
        Sprite l3 = new Line(new Point(415, 150), new Point(415, 60), Color.RED);
        collection.add(l3);
        Sprite l4 = new Line(new Point(415, 230), new Point(415, 320), Color.RED);
        collection.add(l4);
        Sprite name = new LevelName(this.levelName(), Color.WHITE);
        collection.add(name);
        return collection;
    }

    @Override
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
