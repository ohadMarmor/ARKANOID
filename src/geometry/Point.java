package geometry;

/**
 * @author Ohad Marmor 207481524
 */
public class Point {
    /**
     * this class is called "geometry.Point", and it's represented by 2 doubles (x and y).
     * there are several methods as: distance between the point and the other point,
     * comparing two points and return true if they are equal,and getting the values
     * of the x and the y of the point.
     */
    // fields:
    private double x;
    private double y;
    private final double epsilon = Math.pow(10, -2);

    // constructor
    /**
     * constructor: getting two doubles - x and y.
     * @param x - the x value of the point.
     * @param y - the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    /**
     * method: return the distance between this point and the sent points.
     * @param other - the compared point.
     * @return - double: the distance between the points.
     */
    public double distance(Point other) {
        // calculating according to Pythagorean theorem:
        return Math.sqrt(((other.x - this.x) * (other.x - this.x))
                + ((other.y - this.y) * (other.y - this.y)));
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
    public boolean equalsAssist(double first, double second) {
        double differ = Math.max(first, second) - Math.min(first, second);
        return differ <= epsilon;
    }
    // equals -- return true is the points are equal, false otherwise

    /**
     * in this method, with the help of the equalAssist function, we check if 2 numbers
     * are equal.
     * @param other - the point we want to compare to.
     * @return true if they are equal, otherwise - false.
     */
    public boolean equals(Point other) {
        return equalsAssist(this.getX(), other.getX()) && equalsAssist(this.getY(), other.getY());
    }

    // Return the x and y values of this point
    /**
     * this method is accessor to the value of the x field of the point.
     * @return - the x field of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * this method is accessor to the value of the x field of the point.
     * @return - the x field of the point.
     */
    public double getY() {
        return this.y;
    }

    // change the x and y values of this point
    /**
     * this method changes the value of the x field of the point.
     * @param nX - the new x.
     * @param nY - the new y.
     */
    public void set(double nX, double nY) {
        this.x = nX;
        this.y = nY;
    }
}