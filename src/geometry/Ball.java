package geometry;

import biuoop.DrawSurface;
import elements.Paddle;
import component.CollisionInfo;
import component.GameLevel;
import component.GameEnvironment;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor 207481524
 */
public class Ball implements Sprite {
    /**
     * this class is the class of ball. the ball is represented by the point of its center, its
     * radius and its color.
     */
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point highestCorner;
    private Point lowestCorner;
    private GameEnvironment environment;
    private Paddle p;
    private List<HitListener> hitListeners;

    // constructor
    /**
     * this is a constructor of the ball.
     * @param center - a point. the x and the y of the center of the ball.
     * @param r - int: the length of the ball's radius.
     * @param color - the color of the ball, from: java.awt.Color.
     * @param environment - the gameComponent.GameEnvironment with the list of the collidables.
     * @param paddle - the paddle.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment, Paddle paddle) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.p = paddle;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * this is an another constructor of the ball. this time with two arguments of int,
     * instead of a point.
     * @param x - the x value of the point of the ball's center
     * @param y - the y value of the point of the ball's center
     * @param r - int: the length of the ball's radius.
     * @param color - the color of the ball, from: java.awt.Color.
     * @param environment - the gameComponent.GameEnvironment with the list of the collidables.
     * @param paddle - the paddle.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment environment, Paddle paddle) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.environment = environment;
        this.p = paddle;
        this.hitListeners = new ArrayList<>();

    }
    // accessors:
    /**
     * accessor.
     * @return x value
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * accessor.
     * @return y value
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * accessor.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * accessor.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    // change the place of the ball.
    /**
     * this method is for changing the center point of the ball after it's already has a center.
     * @param x - the x value of the new center.
     * @param y - the y value of the new center.
     */
    public void resetCenter(int x, int y) {
        this.center = new Point(x, y);
    }

    // draw the ball on the given DrawSurface

    /**
     * this method draw a ball with its color.
     * @param surface - the drawn ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }
    // velocity:

    /**
     * this method set the velocity of the ball.
     * @param vel - the velocity we set.
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * this method set the velocity of the ball. this time with 2 doubles.
     * @param dx - the x value of the velocity.
     * @param dy - the y value of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * accessor.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * this method defines the borders for the ball movement. assisting moveOneStep method.
     * @param highCorner - the highest point the ball can get.
     * @param loweCorner - the lowest point the ball can get.
     */
    public void setBoard(Point highCorner, Point loweCorner) {
        this.highestCorner = highCorner;
        this.lowestCorner = loweCorner;
    }

    /**
     * this method defines and returns a line that represents the future movement of the ball.
     * @return a line that represents the future movement of the ball.
     */
    public Line ballTrajectory() {
        Point end = new Point(this.center.getX() + this.getVelocity().getDx()
                , this.center.getY() + this.getVelocity().getDy());
        return new Line(this.center, end);
    }

    /**
     * this method move the ball using applyToPoint. there are conditions for the case it gets to
     * the border.
     */
    public void moveOneStep() {
        // the collidables the ball may hit:
        List<Collidable> collidables = this.environment.getCollidables();
        // before we check which one is the closest point, we want to check that there is any
        // point at all.
        CollisionInfo collInfo = this.environment.getClosestCollision(ballTrajectory());
        if (collInfo != null) {
            Point collision = collInfo.collisionPoint();
            // there is a point of collision - we change the velocity
            if (collision != null) {
                Velocity tempV = this.getVelocity();
                this.setVelocity(collInfo.collisionObject().hit(this, collision, this.getVelocity()));
            }
        }
        // keeping the ball in the board:

        // the left border:
        if (this.center.getX() <= 20) {
            this.resetCenter(this.getX() + 20, this.getY());
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        // the right border:
        if (this.center.getX() >= 780) {
            this.resetCenter(this.getX() - 20, this.getY());
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        // the upper border:
        if (this.center.getY() <= 20) {
            this.resetCenter(this.getX(), this.getY() + 20);
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
        // the bottom border:
        if (this.center.getY() >= 580) {
            this.resetCenter(this.getX(), this.getY() - 20);
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    //

    /**
     * this method is a part of the interface and it calls the method of the moveOneStep.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    //

    /**
     * this method activate the ball in the game.
     * @param g the game we want the ball to be a part of.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game.
     * @param g - the game the ball is in.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
