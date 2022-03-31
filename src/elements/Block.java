package elements;

import biuoop.DrawSurface;
import component.CollisionInfo;
import component.GameLevel;
import component.GameEnvironment;
import geometry.Ball;
import geometry.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ohad Marmor 207481524.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Fields:
    private Rectangle collisionS;
    private java.awt.Color color;
    private GameEnvironment environment;
    private List<HitListener> hitListeners;
    // Constructor:

    /**
     * constructor.
     * @param collisionShape a rectangle.
     * @param color the color of the block.
     * @param e the game environment of the block.
     */
    public Block(Rectangle collisionShape, java.awt.Color color, GameEnvironment e) {
        this.collisionS = collisionShape;
        this.color = color;
        this.environment = e;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor.
     * @param collisionShape a rectangle.
     * @param color the color of the block.
     */
    public Block(Rectangle collisionShape, java.awt.Color color) {
        this.collisionS = collisionShape;
        this.color = color;
    }

    // Return the "collision shape" of the object.

    /**
     * accessor.
     * @return the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionS;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * in this method we return the new velocity to the object that hit the block.
     * we need to check where the object hit the block and make sure the velocity will
     * not get the object blown by another block.
     * @param hitter - the ball that hits the block.
     * @param collisionPoint the point the object hit the block.
     * @param currentVelocity the current velocity of the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // rectangle lines:
        Line upWidth = this.getCollisionRectangle().upWidth();
        Line downWidth = this.getCollisionRectangle().downWidth();
        Line rightHeight = this.getCollisionRectangle().rightHeight();
        Line leftHeight = this.getCollisionRectangle().leftHeight();
        // change only the dx:
        if (upWidth.shortIsInLine(collisionPoint) || downWidth.shortIsInLine(collisionPoint)) {
            Velocity temp = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            Point collisionTemp = new Point(collisionPoint.getX() - 0.5 * currentVelocity.getDx()
                    , collisionPoint.getY() - 0.5 * currentVelocity.getDy());
            // after defining the new velocity, we make sure it doesn't drop the object into an
            // another block:
            Line trajectory = new Line(collisionTemp, temp.applyToPoint(collisionPoint));
            CollisionInfo col = this.environment.getClosestCollision(trajectory);
            // there will be an another collision:
            if (col != null) {
                this.notifyHit(hitter);
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            // there will not be an another collision:
            } else {
                this.notifyHit(hitter);
                return temp;
            }
        } else if (rightHeight.shortIsInLine(collisionPoint) || leftHeight.shortIsInLine(collisionPoint)) {
            // change only the dy:
            Velocity temp = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            Point collisionTemp = new Point(collisionPoint.getX() - 0.5 * currentVelocity.getDx()
                    , collisionPoint.getY() - 0.5 * currentVelocity.getDy());
            // after defining the new velocity, we make sure it doesn't drop the object into an
            // another block:
            Line trajectory = new Line(collisionTemp, temp.applyToPoint(collisionPoint));
            CollisionInfo col = this.environment.getClosestCollision(trajectory);
            // there will be an another collision:
            if (col != null) {
                this.notifyHit(hitter);
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            // there will not be an another collision:
            } else {
                this.notifyHit(hitter);
                return temp;
            }
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }
    // draw the ball on the given DrawSurface

    /**
     * this method draw a block with its color.
     * @param surface - the drawn block.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.collisionS.getUpperLeft().getX(), (int) this.collisionS.getUpperLeft().getY()
                , (int) this.collisionS.getWidth(), (int) this.collisionS.getHeight());
        surface.setColor(Color.WHITE);
        surface.drawRectangle((int) this.collisionS.getUpperLeft().getX(), (int) this.collisionS.getUpperLeft().getY()
                , (int) this.collisionS.getWidth(), (int) this.collisionS.getHeight());
    }

    /**
     * this method is part of the interface.
     */
    @Override
    public void timePassed() {
    }

    // add to game:

    /**
     * in this method we activate the block.
     * @param g - the game we want to have the block.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * in this method we activate the block.
     * @param g - the game we want to have the block.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * checking the hits in the listeners.
     * @param hitter - the ball that hits.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
