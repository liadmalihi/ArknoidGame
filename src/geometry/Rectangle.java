//ID:318948809
package geometry;

import java.util.ArrayList;

/**
 * The type Geometry.Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Geometry.Rectangle.
     * the function create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
    }

    /**
     * Upper right point.
     * the function return the upper right point of the rectangle.
     *
     * @return the point- upper right point
     */
    public Point upperRight() {
        Point point1 = new Point(this.width + this.upperLeft.getX(), this.upperLeft.getY());
        return point1;
    }

    /**
     * Down right point.
     * the function return the down right point of the rectangle.
     *
     * @return the point- down right point
     */
    public Point downRight() {
        Point point1 = new Point(this.width + this.upperLeft.getX(), this.height + this.upperLeft.getY());
        return point1;
    }

    /**
     * Down left point.
     * the function return the down left point of the rectangle.
     *
     * @return the point- down left point
     */
    public Point downLeft() {
        Point point1 = new Point(this.upperLeft.getX(), this.height + this.upperLeft.getY());
        return point1;
    }

    /**
     * Geometry.Line upper line.
     * the function return the upper line of the rectangle.
     *
     * @return the line-upper line of rectangle
     */
    public Line lineUpper() {
        Line line = new Line(this.upperLeft, upperRight());
        return line;
    }

    /**
     * Geometry.Line right line.
     * the function return the right line of the rectangle.
     *
     * @return the line-right line of rectangle
     */
    public Line lineRight() {
        Line line = new Line(upperRight(), downRight());
        return line;
    }

    /**
     * Geometry.Line left line.
     * the function return the left line of the rectangle.
     *
     * @return the line- left line of rectangle
     */
    public Line lineLeft() {
        Line line = new Line(this.upperLeft, downLeft());
        return line;
    }

    /**
     * Geometry.Line down line.
     * the function return the down line of rectangle.
     *
     * @return the line
     */
    public Line lineDown() {
        Line line = new Line(downLeft(), downRight());
        return line;
    }

    /**
     * Intersection points java . util . list.
     * the function return a (possibly empty) List of intersection points with the specified line.
     * It checks if there are cut points by sending a line to the isIntersecting function with another line if it sends
     * the same lines to the intersectionWith function that returns the cut point and then adds the point to the list.
     *
     * @param line the line
     * @return the java . util . list of point
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> point = new ArrayList<Point>();
        //If there is a meeting point with down line
        if (line.isIntersecting(lineDown())) {
            point.add(line.intersectionWith(lineDown()));
        }
        //If there is a meeting point with left line
        if (line.isIntersecting(lineLeft())) {
            point.add(line.intersectionWith(lineLeft()));
        }
        //If there is a meeting point with right line
        if (line.isIntersecting(lineRight())) {
            point.add(line.intersectionWith(lineRight()));
        }
        //If there is a meeting point with upper line
        if (line.isIntersecting(lineUpper())) {
            point.add(line.intersectionWith(lineUpper()));
        }
        return point;
    }

    /**
     * Gets width.
     * the function return the width of the rectangle
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     * the function return the height of the rectangle
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     * the function returns the upper-left point of the rectangle.
     *
     * @return the upper left of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}