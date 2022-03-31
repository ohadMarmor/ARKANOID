package component;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ohad Marmor 207481524
 */
public class GameEnvironment {

    // field:
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c - the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable to the environment.
     * @param c - the new collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * accessor to the list of colidables.
     * @return the list of collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * in this method,we return the collidable and the point as a gameComponent.CollisionInfo
     * which is the closest colission to the movement of the ball.
     * @param trajectory the movement of the ball.
     * @return an assistant function which find the closest one.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> closests = new ArrayList<>();
        // running over the collidables:
        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            // adding the point if it isn't null:
            if (p != null) {
                CollisionInfo info = new CollisionInfo(p, c);
                closests.add(info);
            }
        }
        // calling an assistant function to find the closest point of the list:
        return closestInfoToStart(closests, trajectory.start());
    }

    /**
     * this is an assistant method to the above function. it gets a list of gameComponent.CollisionInfo and a point,
     * and return the closest one.
     * @param closests - list of gameComponent.CollisionInfo.
     * @param p the start point of the trajectory.
     * @return the gameComponent.CollisionInfo with the shortest distance to the point.
     */
    public CollisionInfo closestInfoToStart(List<CollisionInfo> closests, Point p) {
        // initialize:
        double distance = -1;
        CollisionInfo closest = null;
        // run over the list:
        for (CollisionInfo c : closests) {
            double current = c.collisionPoint().distance(p);
            // the second check is for the first time.
            if (current < distance || distance < 0) {
                distance = current;
                closest = c;
            }
        }
        return closest;
    }
}
