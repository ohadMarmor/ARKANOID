package interfaces;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
/**
 * @author Ohad Marmor 207481524.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * will be implemented differently by each class.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * will be implemented differently by each class.
     * @param collisionPoint - the point the object and the collidable meet.
     * @param currentVelocity - the current velocity of the object.
     * @param hitter - the ball that hits the block.
     * @return the new velocity of the object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}