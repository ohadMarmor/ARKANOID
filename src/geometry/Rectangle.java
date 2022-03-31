package geometry;

import java.util.List;
import java.util.ArrayList;
/**
 * @author Ohad Marmor 207481524
 */
public class Rectangle {
    // Fields:
    private Point upperL;
    private double w, h;

    // Create a new rectangle with location and width/height:

    /**
     * this is the constructor of the rectangle.
     * @param upperLeft is the upper left point of the rectangle.
     * @param width is the width of the rectangle.
     * @param height is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperL = upperLeft;
        this.w = width;
        this.h = height;
    }

    // the points of the rectangle:

    /**
     * accessor.
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperL.getX() + this.getWidth(), this.upperL.getY());
    }
    /**
     * accessor.
     * @return the bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        return new Point(this.upperL.getX(), this.upperL.getY() + this.getHeight());
    }
    /**
     * accessor.
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        return new Point(this.upperL.getX() + this.getWidth(), this.upperL.getY() + this.getHeight());
    }

    // the lines of the rectangle:
    /**
     * accessor.
     * @return the upper width line of the rectangle.
     */
    public Line upWidth() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }
    /**
     * accessor.
     * @return the down width line of the rectangle.
     */
    public Line downWidth() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }
    /**
     * accessor.
     * @return the left height line of the rectangle.
     */
    public Line leftHeight() {
        return new Line(this.getUpperLeft(), this.getBottomLeft());
    }
    /**
     * accessor.
     * @return the right height line of the rectangle.
     */
    public Line rightHeight() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }

    // reset place:

    /**
     * this method reset the x place of the rectangle.
     * @param x the change.
     */
    public void resetPlace(double x) {
        this.upperL.set(this.upperL.getX() + x, upperL.getY());
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * in this method we check if the specific line intersects the lines of the rectangle,
     * and add the intersection points, if exist, to a list we return.
     * @param line - the line we check the intersections points with our rectangle.
     * @return a list of points that represent the intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        // the lines of the rectangle:
        Line[] lines = {this.upWidth(), this.downWidth(), this.rightHeight(), this.leftHeight()};
        // the list we store the intersection points:
        List<Point> intersections = new ArrayList<>();
        // we run over all the lines of the rectangle and check if each one of them intersects the line:
        for (Line l : lines) {
            if (l.intersectionWith(line) != null) {
                intersections.add(l.intersectionWith(line));
            }
        }
        return intersections;
    }

    // Return the width and height of the rectangle
    /**
     * accessor.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.w;
    }
    /**
     * accessor.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.h;
    }

    // Returns the upper-left point of the rectangle.
    /**
     * accessor.
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperL;
    }
}
