//ID:318948809
package geometry;

/**
 * The type Geometry.Line.
 */
public class Line {
    /**
     * The Start.
     */
    private Point start;
    /**
     * The End.
     */
    private Point end;

    /**
     * Instantiates a new Geometry.Line.
     * The function inserts the start point into a start variable and the point end to end variable.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Geometry.Line.
     * The function gets 4 integers and sets 2 points to create a segment.
     * Redefines start and end by 2 new points.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Length double.
     * The function calculates the distance between 2 points.
     * Return the length of the line
     *
     * @return the double-length of the line
     */
    public double length() {
        double d;
        d = Math.sqrt(((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX()))
                + ((this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY())));
        return d;
    }

    /**
     * Middle point.
     * The function calculates the midpoint of the line.
     * Returns the middle point of the line
     *
     * @return the point-middle point of the line
     */
    public Point middle() {
        double xm, ym;
        xm = (this.start.getX() + this.end.getX()) / 2;
        ym = (this.end.getY() + this.start.getY()) / 2;
        Point middle = new Point(xm, ym);
        return middle;
    }

    /**
     * Start point.
     * The function checks the starting point according to the small X and Y.
     *
     * @return the point-start point of the line
     */
    public Point start() {
        if (this.start.getX() == this.end.getX()) {
            if (this.start.getY() > this.end.getY()) {
                return end;
            }
            return start;
        }
        if (this.start.getX() > this.end.getX()) {
            return end;
        }
        return start;
    }

    /**
     * End point.
     * The function checks for the branch point according to the large X and Y.
     *
     * @return the point-end point of the line
     */
    public Point end() {
        if (this.start.getX() == this.end.getX()) {
            if (this.start.getY() > this.end.getY()) {
                return start;
            }
            return end;
        }
        if (this.start.getX() > this.end.getX()) {
            return start;
        }
        return end;
    }

    /**
     * Geometry.Point in line boolean.
     *
     * @param p    the p
     * @param line the line
     * @return the boolean
     */
    public boolean pointInLine(Point p, Line line) {
        double m, epsilon = 0.0001;
        if (Math.abs(line.start().getX() - line.end().getX()) <= epsilon) {
            if (Math.abs(line.end().getX() - p.getX()) <= epsilon
                    && p.getY() >= line.start().getY() - epsilon && p.getY() <= line.end().getY() + epsilon) {
                return true;
            }
            return false;
        }
        if (Math.abs(line.start().getY() - line.end().getY()) <= epsilon) {
            if (Math.abs(line.end().getY() - p.getY()) <= epsilon && p.getX() >= line.start().getX() - epsilon
                    && p.getX() <= line.end().getX() - epsilon) {
                return true;
            }
            return false;
        }
        m = (line.start().getY() - line.end().getY()) / (line.start().getX() - line.end().getX());
        if (p.getY() - line.start().getY() == m * (p.getX() - line.start().getX())
                && p.getX() >= line.start().getX() - epsilon && p.getX() <= line.end().getX() + epsilon) {
            return true;
        }
        return false;
    }

    /**
     * Perpendicular x boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean perpendicularX(Line other) {
        double m, y;
        double tSX = this.start().getX(), tSY = this.start().getY(), tEX = this.end().getX(), tEY = this.end().getY();
        double oSX = other.start().getX(), oSY = other.start().getY(),
                oEX = other.end().getX(), oEY = other.end().getY();
        //If the x is in the range of the x values of the line
        if (oSX <= tSX && oEX >= tSX) {
            m = (oSY - oEY) / (oSX - oEX);
            y = m * (tSX - oSX) + oSY;
            //if the y is in the range of the y values of the line
            if (y >= tSY && y <= tEY) {
                return true;
            }
        }
        return false;

    }

    /**
     * Is intersecting boolean.
     * The function checks if the 2 lines in the defined sections are cut once.
     * The function checks by calculating a straight equation, checks whether the straight sections are cut, and if so,
     * if they are cut in the defined section.
     *
     * @param other the other- other line
     * @return the boolean-true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double m, x, yO, yT, mO, mT, y, epsilon = 0.001;
        double tSX = this.start().getX(), tSY = this.start().getY(), tEX = this.end().getX(), tEY = this.end().getY();
        double oSX = other.start().getX(), oSY = other.start().getY(),
                oEX = other.end().getX(), oEY = other.end().getY();
        if (other.start().equals(other.end())) {
            return pointInLine(other.end, this);
        }
        if (this.start().equals(this.end())) {
            return pointInLine(this.start, other);
        }
        //If the 2 lines are vertical to the X axis
        if (Math.abs(tSX - tEX) <= epsilon && Math.abs(oSX - oEX) <= epsilon) {
            //if they didn't have same value of X
            if (oSX != tSX) {
                return false;
            }
            //if they have same value of X
            if (Math.abs(tSY - oEY) <= epsilon || Math.abs(oSY - tEY) <= epsilon) {
                return true;
            }
            return false;
        }
        //if this line have same value of x
        if (Math.abs(tSX - tEX) <= epsilon) {
            return this.perpendicularX(other);
        }
        //if other line have same value of x
        if (Math.abs(oSX - oEX) <= epsilon) {
            return other.perpendicularX(this);
        }
        mT = (tSY - tEY) / (tSX - tEX);
        mO = (oSY - oEY) / (oSX - oEX);
        //If the 2 lines are vertical to the Y axis
        if (mO == mT) {
            //if they have same value of X
            if (this.start().equals(other.end()) || other.start().equals(this.end())) {
                return true;
            }
            return false;
        }

        //The Y values in the equations
        yO = mO * ((-1) * oSX) + oSY;
        yT = mT * ((-1) * tSX) + tSY;
        m = mT - mO;
        x = (yO - yT) / m;
        y = mO * (x - oSX) + oSY;
        //If the point is within the line area
        if ((y <= Math.max(tSY, tEY) + epsilon && y >= Math.min(tSY, tEY) - epsilon)
                && (y <= Math.max(oSY, oEY) + epsilon && y >= Math.min(oEY, oSY) - epsilon)) {
            if (x >= tSX - epsilon && x <= tEX + epsilon && x >= oSX - epsilon && x <= oEX + epsilon) {
                return true;
            }
        }
        return false;
    }


    /**
     * Intersection with point.
     * The function checks if the lines are cut in a section, and if so at what point.
     * By calling the function isIntersecting - if false returns - the lines are not cut and return null.
     * Otherwise, check the cut point by comparing 2 straight equations.
     *
     * @param other the other
     * @return the point-intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m, x, y1, y2, m1, m2, y, epsilon = 0.001;
        if (!isIntersecting(other)) {
            return null;
        }
        //If the X values in the line are equal
        if (Math.abs(this.start.getX() - this.end.getX()) <= epsilon) {
            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            y2 = m2 * other.start.getX() - other.start.getY();
            x = this.start.getX();
            y = m2 * x - y2;
            //If the X values in the other line are equal
        } else if (Math.abs(other.start.getX() - other.end.getX()) <= epsilon) {
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            y1 = m1 * this.start.getX() - this.start.getY();
            x = other.start.getX();
            y = m1 * x - y1;
        } else {
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            //If the gradients are equal
            if (m1 == m2) {
                if (this.start().equals(other.end())) {
                    return new Point(this.start().getX(), this.start().getY());
                }
                if (other.start().equals(this.end())) {
                    return new Point(other.start().getX(), other.start().getY());
                }
            }
            //The Y values in the equations
            y1 = m1 * this.start.getX() - this.start.getY();
            y2 = m2 * other.start.getX() - other.start.getY();
            m = m2 - m1;
            x = (y2 - y1) / m;
            y = m1 * x - y1;
        }
        Point point = new Point(x, y);
        return point;
    }

    /**
     * Equals boolean.
     * The function checks the 2 lines equally, by comparing the starting and ending points of each.
     *
     * @param other the other
     * @return the boolean- return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        double epsilon = 0.001;
        if (this == null || other == null) {
            return false;
        }
        if (Math.abs(this.start.getX() - other.start.getX()) <= epsilon
                && Math.abs(this.start.getY() - other.start.getY()) <= epsilon
                && Math.abs(this.end.getX() - other.end.getX()) <= epsilon
                && Math.abs(this.end.getY() - other.end.getY()) <= epsilon) {
            return true;
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rect-Geometry.Rectangle rect
     * @return the point -closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.start, this.end);
        //If there is no meeting point
        if (rect.intersectionPoints(line) == null) {
            return null;
        } else {
            java.util.List<Point> points = rect.intersectionPoints(line);
            double distance, d;
            Point closer;
            Point start1 = line.start;
            Point newPoint = line.end;
            distance = start1.distance(newPoint);
            closer = newPoint;
            //A loop that goes through all the meeting points
            for (int i = 0; i < rect.intersectionPoints(line).size(); i++) {
                newPoint = points.get(i);
                d = start1.distance(newPoint);
                if (d < distance) {
                    distance = d;
                    closer = newPoint;
                }
            }
            return closer;
        }
    }
}
