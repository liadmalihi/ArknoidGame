//ID:318948809
package geometry;

/**
 * The type Geometry.Point.
 */
public class Point {
    /**
     * The X.
     */
    private double x;
    /**
     * The Y.
     */
    private double y;

    /**
     * Instantiates a new Geometry.Point.
     * The function accepts 2 numbers and puts X to X and Y to Y.
     *
     * @param x the x of point
     * @param y the y of point
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * The function receives a point and calculates the distance between it and the given point.
     *
     * @param other the other-of point
     * @return the double- return the distance of this point to the other point
     */
    public double distance(Point other) {
        double d;
        d = Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
        return d;
    }

    /**
     * Equals boolean.
     * The function receives a point and check if this point is same of the given point.
     *
     * @param other the other-of point
     * @return the boolean- return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = 0.00001;
        if (this == null || other == null) {
            return false;
        }
        if (Math.abs(this.x - other.x) <= epsilon && Math.abs(this.y - other.y) <= epsilon) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     * the function return the value of x of point
     *
     * @return the x- x of point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets x.
     * change the value of x
     *
     * @param nx the new x
     */
    public void setX(double nx) {
        this.x = nx;
    }

    /**
     * Gets y.
     * the function return the value of y of point
     *
     * @return the y-y of point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets y.
     * change the value of y
     *
     * @param ny the new y
     */
    public void setY(double ny) {
        this.y = ny;
    }
}
