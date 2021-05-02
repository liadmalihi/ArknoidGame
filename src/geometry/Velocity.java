//ID:318948809
package geometry;

/**
 * The type Geometry.Velocity.
 */
// Geometry.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    /**
     * The Dx.
     */
    private double dx;
    /**
     * The Dy.
     */
    private double dy;

    /**
     * Instantiates a new Geometry.Velocity.
     * The function accepts 2 numbers and puts dx to dx and dy to dy.
     *
     * @param dx the dx- new x of point
     * @param dy the dy- new y of point
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     * The function calculates the velocity using gradient and angle.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
// constructor
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        // change to dx and dy therms.
        double dx = speed * Math.sin(angle);
        double dy = -speed * Math.cos(angle);
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point point.
     * the function take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p the p-point
     * @return the point-new point
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }

    /**
     * Gets dx.
     * the function return the value of dx
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets dx.
     *
     * @param nDx the n dx
     */
    public void setDx(double nDx) {
        this.dx = nDx;
    }

    /**
     * Gets dy.
     * the function return the value of dy
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets dy.
     *
     * @param nDy the n dy
     */
    public void setDy(double nDy) {
        this.dy = nDy;
    }
}
