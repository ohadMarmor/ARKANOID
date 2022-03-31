package component;

import biuoop.DrawSurface;
import biuoop.GUI;
import elements.Block;
import elements.LevelName;
import elements.Paddle;
import geometry.Ball;
import geometry.Velocity;
import geometry.Counter;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * @author Ohad Marmor 207481524
 */
public class GameLevel implements Animation {
    // fields:
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor k;
    private biuoop.GUI gui;
    private Counter blocksNumber;
    private Counter ballsNumber;
    private BlockRemover listenerBlock;
    private BallRemover listenerBall;
    private Counter score;
    private ScoreTrackingListener s;
    private ScoreIndicator showScore;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation info;

    /**
     * constructor.
     * @param i - the level we implement.
     * @param e - the game environment.
     * @param c - the counter that counts from level to level.
     * @param g - the gui of the whole game.
     */
    public GameLevel(LevelInformation i, GameEnvironment e, Counter c, GUI g) {
        this.info = i;
        this.environment = e;
        this.score = c;
        this.gui = g;
    }

    /**
     * getter.
     * @return the number of blocks in this level (at any point).
     */
    public Counter getBlocksNumber() {
        return this.blocksNumber;
    }

    /**
     * getter.
     * @return the number of balls in this level (at any point).
     */
    public Counter getBallsNumber() {
        return this.ballsNumber;
    }
    /**
     * add a new collidable to the collidable list.
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * add a new collidable to the collidable list.
     * @param sprite the new sprite.
     */
    public void addSprite(Sprite sprite) {
        sprites.addSprite(sprite);
    }
    /**
     * remove a new collidable to the collidable list.
     * @param sprite the new sprite.
     */
    public void removeSprite(Sprite sprite) {
        sprites.removeSprite(sprite);
    }
    /**
     * accessor.
     * @return the gui of the game.
     */
    public biuoop.GUI getGui() {
        return this.gui;
    }

    // Initialize a new game: create the Blocks and geometry.Ball (and elements.Paddle)
    // and add them to the game.

    /**
     * in this method we initialize the game. we activate all the components, and give them value,
     * and implement the addToGame method for each sprite after initialize it.
     */
    public void initialize() {
        // constants:
        int width = 800, height = 600, radius = 5;
        Rectangle recPaddle = new Rectangle(new Point(300, 570), this.info.paddleWidth(), 10);
        double spaceLeft = 780, spaceBottom = 580, space = 20;
        this.sprites = new SpriteCollection();
        this.sprites.addSprite(this.info.getBackground());
        for (Sprite sprite : this.info.collectionS()) {
            this.sprites.addSprite(sprite);
        }
        this.k = this.getGui().getKeyboardSensor();
        s = new ScoreTrackingListener(score);
        Paddle p = new Paddle(recPaddle, this, this.info.paddleSpeed(), this.info.paddleColor());
        p.addToGame(this);
        for (Velocity v : this.info.initialBallVelocities()) {
            Ball b = new Ball(new Point(400, 300), radius, Color.WHITE, this.environment, p);
            b.setVelocity(v);
            b.setBoard(new Point(width, height), new Point(0, 0));
            b.addToGame(this);
        }
        // create the ball listener:
        this.ballsNumber = new Counter(this.info.numberOfBalls());
        listenerBall = new BallRemover(this, this.ballsNumber);
        Block[] borders = new Block[4];
        borders[0] = new Block(new Rectangle(new Point(0, 0), width, space), Color.GRAY, this.environment);
        borders[1] = new Block(new Rectangle(new Point(0, spaceBottom), width, 20), Color.BLUE
                , this.environment);
        // create the blowing border:
        borders[1].addHitListener(this.listenerBall);
        borders[2] = new Block(new Rectangle(new Point(0, 0), space, height), Color.GRAY, this.environment);
        borders[3] = new Block(new Rectangle(new Point(spaceLeft, 0), space, height), Color.GRAY, this.environment);
        this.sprites.addSprite(new LevelName(this.info.levelName(), Color.WHITE));
        for (Block b : borders) {
            b.addToGame(this);
        }
        this.blocksNumber = new Counter(this.info.numberOfBlocksToRemove());
        this.listenerBlock = new BlockRemover(this, this.blocksNumber);
        for (Block b : this.info.blocks()) {
            b.addToGame(this);
            b.addHitListener(this.listenerBlock);
            b.addHitListener(this.s);

        }
        this.showScore = new ScoreIndicator(this.score, this.environment);
        this.showScore.addToGame(this);
        this.runner = new AnimationRunner(this.gui, 20);
    }

    // Run the game -- start the animation loop.

    /**
     * in this method we create the loop that run the animation and the movement of the components in
     * the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(750, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * remove a collidable (ball or block).
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        new LevelName(this.info.levelName(), Color.WHITE).drawOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksNumber.getValue() == 0 || this.ballsNumber.getValue() == 0) {
            this.running = false;
        }
        if (this.k.isPressed("p")) {
            this.runner.run(new PauseScreen(this.k));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}