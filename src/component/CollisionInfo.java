package component;

import geometry.Point;
import interfaces.Collidable;

/**
 * @author Ohad Marmor 207481524.
 */
public class CollisionInfo {

    // Fields:
    private Point collisionP;
    private Collidable collisionO;

    // Constructor:

    /**
     * constructor.
     * @param collisionPoint - the collision point of the object and the collidable.
     * @param collisionObject - collision Object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionP = collisionPoint;
        this.collisionO = collisionObject;
    }
    // the point at which the collision occurs.

    /**
     * accessor.
     * @return the collision point of the collisionInfo.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }
    // the colloidal object involved in the collision.

    /**
     * accessor.
     * @return the collision object of the collisionInfo.
     */
    public Collidable collisionObject() {
        return this.collisionO;
    }
}
