package geometry;

import biuoop.DrawSurface;
import component.GameLevel;
import interfaces.Sprite;

import java.awt.*;
import java.util.List;

/**
 * @author Ohad Marmor 207481524
 */
public class Line implements Sprite {
    /**
     * this class is the geometry.Line class, which receives two points, start and end (each
     * one of them is 2 doubles), and has several methods.
     */
    // fields:
    private Point start, end;
    private double x1, y1, x2, y2;
    private final double epsilon = Math.pow(10, -2);
    private Color color;
    // constructors
    /**
     * this constructor is for the ideal case - getting two points,
     * start and end. the fields of the x1, y1, x2, y2 receive the
     * x and y of the points accordingly.
     * @param start - the start point.
     * @param end - the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = this.start.getX();
        this.y1 = this.start.getY();
        this.x2 = this.end.getX();
        this.y2 = this.end.getY();
    }

    /**
     * this is is another constructor for the case of receiving 4 doubles
     * instead of 2 points.
     * @param x1 - the x of the start.
     * @param y1 - the y of the start.
     * @param x2 - the x of the end.
     * @param y2 - the y of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * constructor.
     * @param start - the start point of the line.
     * @param end - the end point of the line.
     * @param c - the color the line will be drawn.
     */
    public Line(Point start, Point end, Color c) {
        this.start = start;
        this.end = end;
        this.x1 = this.start.getX();
        this.y1 = this.start.getY();
        this.x2 = this.end.getX();
        this.y2 = this.end.getY();
        this.color = c;
    }

    // Return the length of the line
    /**
     * this method returns the length of the line using the
     * point's method - distance.
     * @return - the distance of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    /**
     * this method return the middle point of the line.
     * @return - the middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // Returns the start point of the line
    /**
     * this method is an accessor to the start point of the line.
     * @return - the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    /**
     * this method is an accessor to the end point of the line.
     * @return - the end point of the line.
     */
    public Point end() {
        return this.end;
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
        double differ = Math.max(first, second) - Math.min(first, second);
        return differ <= epsilon;
    }

    // return true if the lines touch each other in one point only
    /**
     * this methods is an assistant for isIntersecting and intersecting with methods.
     * it's for the case that two lines are parallel and on the same line, and we
     * want to know if the only touch each other in the edge, or also merge.
     * @param other - the compared line
     * @return - a point if it's in both edges, and null if the lines merge.
     */
    private Point touchEdge(Line other) {
        // cases the start point is the edge:
        if (this.start.equals(other.start) && !(isInLine(this, other, this.end.getX(), this.end.getY())
                || isInLine(this, other, other.end.getX(), other.end.getY()))) {
            return this.start;
        }
        if (this.start.equals(other.end) && !(isInLine(this, other, this.end.getX(), this.end.getY())
                || isInLine(this, other, other.start.getX(), other.start.getY()))) {
            return this.start;
        }
        // cases the end point is the edge:
        if (this.end.equals(other.end) && !(isInLine(this, other, this.start.getX(), this.start.getY())
                || isInLine(this, other, other.start.getX(), other.start.getY()))) {
            return this.end;
        }
        if (this.end.equals(other.start) && !(isInLine(this, other, this.start.getX(), this.start.getY())
                || isInLine(this, other, other.end.getX(), other.end.getY()))) {
            return this.end;
        }
        // case the lines merge:
        return null;
    }
    // return true if the point is on the lines

    /**
     * this function receives two lines and two doubles that represent a point, and return
     * true if the point is on both lines. this function is used only after we know for sure
     * that the point is on both of equations (cutting point). in order to simplify the conditions,
     * we use 4 boolean variables.
     * @param first  - the first line.
     * @param second - the second line.
     * @param x - the x value of the point.
     * @param y - the x value of the point.
     * @return - true if the point is on both lines, and false otherwise.
     */
    public boolean isInLine(Line first, Line second, double x, double y) {
        // these variables are for making sure the point is on the first line
        // checking the x of the point:
        boolean xInLineT = (x >= Math.min(first.start.getX(), first.end.getX())
                && x <= Math.max(first.start.getX(), first.end.getX()));
        // checking the y of the point:
        boolean yInLineT = (y >= Math.min(first.start.getY(), first.end.getY())
                && y <= Math.max(first.start.getY(), first.end.getY()));
        // these variables are for making sure the point is on the second line
        // checking the x of the point:
        boolean xInLineO = (x >= Math.min(second.start.getX(), second.end.getX())
                && x <= Math.max(second.start.getX(), second.end.getX()));
        // checking the y of the point:
        boolean yInLineO = (y >= Math.min(second.start.getY(), second.end.getY())
                && y <= Math.max(second.start.getY(), second.end.getY()));
        // if all the variables are true - it returns true, otherwise - false.
        return xInLineT && yInLineT && xInLineO && yInLineO;
    }
    //

    /**
     * this is the short version for the function isInLine.
     * @param p the checked point.
     * @return true if it's on the line and false if it isn't
     */
    public boolean shortIsInLine(Point p) {
        // these variables are for making sure the point is on the first line
        // checking the x of the point:
        boolean xInLine = (p.getX() >= Math.min(this.start.getX(), this.end.getX())
                && p.getX() <= Math.max(this.start.getX(), this.end.getX()));
        // checking the y of the point:
        boolean yInLine = (p.getY() >= Math.min(this.start.getY(), this.end.getY())
                && p.getY() <= Math.max(this.start.getY(), this.end.getY()));
        return xInLine && yInLine;
    }
    // case one of the lines is parallel to the y-axis
    /**
     * this function is for the case the first line is parallel to the y-axis. there are
     * several options for this case (for example: the other line is also parallel to y-axis).
     * this function is helped by equalAssist, touchEdge and isInLine functions. the returned
     * value of this function goes to the intersectionWith method.
     * @param first - the first line.
     * @param second - the second line.
     * @return - a point - if the lines intersect. null - if they don't.
     */
    public Point parallelToY(Line first, Line second) {
        double incline;
            // case the second line is also parallel to y-axis
            if (equalsAssist(second.start.getX(), second.end.getX())) {
                // case the lines have the same x value
                if (equalsAssist(first.start.getX(), second.end.getX())) {
                    // if they touch each other in the edge
                    return first.touchEdge(second);
                } else {
                    // if they merge
                    return null;
                }
            }
        // case the second line isn't parallel to the y-axis
        // calculating the incline of the second line:
        incline = ((second.start.getY() - second.end.getY())
                / (second.start.getX() - second.end.getX()));
        // calculating the free organ of the equation:
        double otherX = incline * (-1) * second.start.getX() + second.start.getY();
        // calculating the y value of the cutting point:
        double intersectY = incline * first.start.getX() + otherX;
        // checking the point is on both lines:
        if (isInLine(first, second, first.start.getX(), intersectY)) {
            return new Point(first.start.getX(), intersectY);
        } else {
            return null;
        }
    }

    // Returns true if the lines intersect, false otherwise

    /**
     * this method checks if this line is intersecting with another line. first, if it gets
     * a point using the intersectionWith function, it returns true, otherwise, there are several
     * cases that need to be checked. if the lined do intersect - it returns true. otherwise - false.
     * @param other - the compared line.
     * @return - true: lines intersect. false: lines don't intersect.
     */
    public boolean isIntersecting(Line other) {
        // the simple case of getting point and know for sure the lines intersect:
        if (this.intersectionWith(other) != null) {
            return true;
        // several cases that need to be checked:
        } else {
            // if the lines are vertical
            if (equalsAssist(this.start.getX(), this.end.getX())
                    && equalsAssist(other.start.getX(), other.end.getX())) {
                // returns true if the lines have the same and also merge with each other
                return equalsAssist(this.start.getX(), other.end.getX())
                        && (isInLine(this, other, this.start.getX(), this.start.getY())
                        || isInLine(this, other, this.end.getX(), this.end.getY()));
            }
            // case one of the lines is vertical and the other isn't
            if (equalsAssist(this.start.getX(), this.end.getX())
                    ^ equalsAssist(other.start.getX(), other.end.getX())) {
                return false;
            }
            // calculating the inclines of the lines:
            double inclineThis = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
            double inclineOther = ((other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX()));
            // these X's are the free items of the equations, by the formula: y-y1 = m(x-x1)
            double thisX = inclineThis * (-1) * this.start.getX() + this.start.getY();
            double otherX = inclineOther * (-1) * other.start.getX() + other.start.getY();
            // case the inclines are equal, and the fre items are equal (which means the lines are parallel):
            if (equalsAssist(inclineThis, inclineOther) && equalsAssist(thisX, otherX)) {
                // returns true if the lines merge:
                return isInLine(this, other, this.start.getX(), this.start.getY())
                        || isInLine(this, other, this.end.getX(), this.end.getY())
                        || isInLine(this, other, other.start.getX(), other.start.getY())
                        || isInLine(this, other, other.end.getX(), other.end.getY());
            } else {
                return false;
            }
        }
    }
    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * this method checks if the lines intersect, and if they do, it returns the cutting point,
     * and if they don't, it returns null. this method is also an assistant method for the
     * isIntersecting method.
     * @param other - the compared line.
     * @return - point: if the lines intersect. null: if they don't.
     */
    public Point intersectionWith(Line other) {
        // checking if the lines are equal:
        if (this.equals(other)) {
            return null;
        }
        // inclines of the lines
        double  inclineThis, inclineOther;
        // if the 'this' line is vertical
        if (equalsAssist(this.start.getX(), this.end.getX())) {
            return parallelToY(this, other);
        }
        // if the 'other' line is vertical
        if (equalsAssist(other.start.getX(), other.end.getX())) {
            return parallelToY(other, this);
        }
        // calculating the inclines of the lines:
        inclineThis = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        inclineOther = ((other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX()));
        // these X's are the free items of the equations, by the formula: y-y1 = m(x-x1)
        double thisX = inclineThis * (-1) * this.start.getX() + this.start.getY();
        double otherX = inclineOther * (-1) * other.start.getX() + other.start.getY();
        // case the lines aren't parallel:
        if (!equalsAssist(inclineThis, inclineOther)) {
            // calculating the x and y of the intersection:
            double intersectX = (otherX - thisX) / (inclineThis - inclineOther);
            double intersectY = inclineThis * intersectX + thisX;
            // checking the point is on both lines:
            if (isInLine(this, other, intersectX, intersectY)) {
                return new Point(intersectX, intersectY);
            }
        } else {
            // case the lines equations are equal:
            if (equalsAssist(thisX, otherX)) {
                // checking the lines don't merge:
                return this.touchEdge(other);
            }
        }
        return null;
    }

    // equals -- return true is the lines are equal, false otherwise

    /**
     * this method checks if this line and another line are equal using the equalAssist function.
     * in order to simplify the cases, there are 4 boolean variables.
     * @param other - the compared line
     * @return - true: lines are equal. false: lines are different.
     */
    public boolean equals(Line other) {
        // case: this.start = other.start and this.end = other.end.
        boolean condition1 = equalsAssist(this.start.getX(), other.start.getX())
                && equalsAssist(this.end.getX(), other.end.getX());
        boolean condition2 = equalsAssist(this.start.getY(), other.start.getY())
                && equalsAssist(this.end.getY(), other.end.getY());
        // case: this.start = other.end and this.end = other.start.
        boolean condition3 = equalsAssist(this.end.getX(), other.start.getX())
                && equalsAssist(this.start.getX(), other.end.getX());
        boolean condition4 = equalsAssist(this.end.getY(), other.start.getY())
                && equalsAssist(this.start.getY(), other.end.getY());
        return (condition1 && condition2) || (condition3 && condition4);
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * this method get a rectangle, which has 4 lines, and check which point is the closest
     * intersection of the line and one of the rectangle's lines.
     * @param rect - the rectangle we check.
     * @return the closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        List<Point> intersections = rect.intersectionPoints(this);
        Point closest = null;
        double closestD = -1;
        for (Point p : intersections) {
            if (p.distance(this.start) < closestD || closestD < 0) {
                closestD = p.distance(this.start);
                closest = p;
            }
        }
        return closest;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}