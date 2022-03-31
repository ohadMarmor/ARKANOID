package elements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import component.GameLevel;
import geometry.Ball;
import geometry.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Ohad Marmor 207481524.
 */
public class Paddle implements Sprite, Collidable {
    // Fields:
    private Rectangle collisionS;
    private java.awt.Color color;
    //private biuoop.KeyboardSensor keyboard;
    private GameLevel g;
    private final double epsilon = Math.pow(10, -2);
    private int speedPaddle;

    // constructor:

    /**
     * constructor.
     * @param c - the shape of the paddle.
     * @param gameLevel - the game the paddle is part of.
     * @param s - the speed of the paddle.
     * @param co - the color the paddle will be drawn.
     */
    public Paddle(Rectangle c, GameLevel gameLevel, int s, Color co) {
        this.collisionS = c;
        this.g = gameLevel;
        this.speedPaddle = s;
        this.color = co;
    }
    // equalsAssist - this is an assist function for comparing two points.
    /**
     * in this function, using the variable of epsilon, we can compare between
     * two doubles, and if the difference between them is smaller than epsilon -
     * it returns true.
     * @param first - the first double
     * @param second - the second double
     * @return true if they have a difference that is smaller than epsilon.
     */
    private boolean equalsAssist(double first, double second) {
        return Math.abs(first - second) <= epsilon;
    }

    /**
     * this method change the place of the paddle by moving it left.
     */
    public void moveLeft() {
        double current = this.collisionS.getUpperLeft().getX();
        // limit:
        if (current >= 20) {
            this.collisionS.resetPlace(-this.speedPaddle);
        }
    }
    /**
     * this method change the place of the paddle by moving it right.
     */
    public void moveRight() {
        double current = this.collisionS.getUpperRight().getX();
        // limit:
        if (current <= 780) {
            this.collisionS.resetPlace(this.speedPaddle);
        }
    }

    // interfaces.Sprite

    /**
     * in this method we check if the user moved the paddle.
     */
    public void timePassed() {
        biuoop.KeyboardSensor k = this.g.getGui().getKeyboardSensor();
        if (k.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (k.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * this method draw a paddle with its color.
     * @param d - the drawn block.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.collisionS.getUpperLeft().getX(), (int) this.collisionS.getUpperLeft().getY()
                , (int) this.collisionS.getWidth(), (int) this.collisionS.getHeight());
    }

    // interfaces.Collidable:
    /**
     * accessor.
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionS;
    }
    // hit:
    /**
     * in this method we return the new velocity to the object that hit the paddle.
     * unlike the block, here we consider the spot of the paddle the object hit.
     * @param hitter - the ball that hits the block.
     * @param collisionPoint the point the object hit the paddle.
     * @param currentVelocity the current velocity of the paddle.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed = 15;
        // rectangle lines:
        Line upWidth = this.getCollisionRectangle().upWidth();
        Line downWidth = this.getCollisionRectangle().downWidth();
        Line rightHeight = this.getCollisionRectangle().rightHeight();
        Line leftHeight = this.getCollisionRectangle().leftHeight();
        double divider = upWidth.length() / 5;
        Line[] lines = new Line[5];
        // dividing the elements.Paddle:
        for (int i = 0; i < 5; i++) {
            Point start = new Point(upWidth.start().getX() + divider * i, upWidth.start().getY());
            Point end = new Point(upWidth.start().getX() + divider * (i + 1), upWidth.start().getY());
            lines[i] = new Line(start, end);
        }
        // change only the dx:
        if (rightHeight.shortIsInLine(collisionPoint) || leftHeight.shortIsInLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        // change only the dy: now we consider the spot of the paddle:
        } else if (upWidth.shortIsInLine(collisionPoint) || downWidth.shortIsInLine(collisionPoint)) {
            if (lines[0].shortIsInLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (lines[1].shortIsInLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (lines[2].shortIsInLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(0, speed);
            } else if (lines[3].shortIsInLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else if (lines[4].shortIsInLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, speed);
            } else {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }

    // Add this paddle to the game.

    /**
     * this method activates the paddle and adds it to the game.
     * @param gameLevel - the game we add the paddle.
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}