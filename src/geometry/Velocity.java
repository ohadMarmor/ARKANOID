package geometry;

/**
 * @author Ohad Marmor 207481524
 */
// geometry.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    /**
     * this class is the class of velocity for the movement of the ball.
     */
    private double dx, dy;
    // constructor

    /**
     * this is a constructor for building the velocity.
     * @param dx - the dx value of the velocity.
     * @param dy - the dy value of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    // constructor

    /**
     * this method is for building the velocity by angle and speed. then, we calculate the
     * values of dx and dy by the vectors of the speed.
     * @param angle - a double between 0 and 360.
     * @param speed - the speed of the ball.
     * @return the velocity with the fixed values of the dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // converting the angle to radians:
        double fixedAngle = Math.toRadians(angle);
        // the vector on the x-axis:
        double dx = speed * Math.sin(fixedAngle);
        // the vector on the y-axis:
        double dy = speed * -Math.cos(fixedAngle);
        return new Velocity(dx, dy);
    }

    /**
     * accessor.
     * @return dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * accessor.
     * @return dy.
     */
    public double getDy() {
        return this.dy;
    }
    // change the x:
    /**
     * this method is for changing the dx after initializing it.
     * @param newDx - the new value of dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    // change the y:
    /**
     * this method is for changing the dy after initializing it.
     * @param newDy - the new value of dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * this method gets point and add it to the velocity.
     * @param p - the point values we want to add the velocity.
     * @return a point with the addition of the x and y of p.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}