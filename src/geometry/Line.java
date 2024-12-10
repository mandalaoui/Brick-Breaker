//Omer Mandalaoui 211695598
package geometry;

/**
 * The geometry.geometry.Line class represents a line segment in a 2D space defined by two points.
 * It includes methods to calculate the length of the line, find the middle point,
 * check if the line intersects with another line, find the intersection point,
 * and check if a point lies on the line segment.
 */
public class Line {
    // fields
    private final Point start;
    private final Point end;
    private final double length;
    private static final double EPSILON = 1e-7;

    /**
     * Constructs a geometry.geometry.Line with the specified start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.distance(end);
    }

    /**
     * Constructs a geometry.geometry.Line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = start.distance(end);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.length;
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if the line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (isIntersectingWith(other) != null);
    }

    /**
     * Checks if a point lies on the line segment.
     *
     * @param point the point to check
     * @return true if the point is on the line segment, false otherwise
     */
    public boolean isInLine(Point point) {
        return isInLine(point.getX(), point.getY());
    }

    /**
     * Checks if the given coordinates lie on the line segment.
     *
     * @param x1 the x-coordinate to check
     * @param y1 the y-coordinate to check
     * @return true if the coordinates lie on the line segment, false otherwise
     */
    public boolean isInLine(double x1, double y1) {
        return (isBetween(x1, this.start.getX(), this.end.getX()) && isBetween(y1, this.start.getY(), this.end.getY()));
    }

    /**
     * Checks if a value is between two other values.
     *
     * @param a      the value to check
     * @param first  the first bound
     * @param second the second bound
     * @return true if the value is between the bounds, false otherwise
     */
    public boolean isBetween(double a, double first, double second) {
        return (a >= Math.min(first, second) - EPSILON && a <= Math.max(first, second) + EPSILON);
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first line to check for intersection
     * @param other2 the second line to check for intersection
     * @return true if this line intersects both lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (isIntersecting(other1) && isIntersecting(other2));
    }

    /**
     * Finds the intersection point with another line, if it exists.
     *
     * @param other the other line to find intersection with
     * @return the intersection point if lines intersect, null otherwise
     */
    public Point isIntersectingWith(Line other) {
        // Check if this line is vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
            // Check if the other line is vertical
            if (Math.abs(other.start.getX() - other.end.getX()) < EPSILON) {
                // Check for overlapping points
                if (this.isInLine(other.start)) {
                    return other.start;
                }
                if (this.isInLine(other.end)) {
                    return other.start;
                }
                return null;
            } else {
                // Check if the other line crosses this line's X-axis
                if (isBetween(this.start.getX(), other.start.getX(), other.end.getX())) {
                    // Calculate the equation of the other line
                    double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                    double b2 = other.start.getY() - (m2 * other.start.getX());
                    double y2 = m2 * this.start.getX() + b2;

                    if (isBetween(y2, this.start.getY(), this.end.getY())) {
                        return new Point(this.start.getX(), y2);
                    }
                }
                return null;
            }
        } else if (Math.abs(other.start.getX() - other.end.getX()) < EPSILON) {
            // Check if the other line crosses this line's X-axis
            if (isBetween(other.start.getX(), this.start.getX(), this.end.getX())) {
                // Calculate the equation of this line
                double m2 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                double b2 = this.start.getY() - (m2 * this.start.getX());
                double y2 = m2 * other.start.getX() + b2;

                if (isBetween(y2, other.start.getY(), other.end.getY())) {
                    return new Point(other.start.getX(), y2);
                }
            }
            return null;
        }

        // Calculate slopes and intercepts of both lines
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double b1 = this.start.getY() - (m1 * this.start.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double b2 = other.start.getY() - (m2 * other.start.getX());

        // Check if lines are parallel
        if (Math.abs(m1 - m2) < EPSILON) {
            if (this.isInLine(other.start)) {
                return other.start;
            }
            if (this.isInLine(other.end)) {
                return other.end;
            }
            return null;
        }

        // Calculate intersection point
        double sharedX = (b2 - b1) / (m1 - m2);
        double sharedY = m1 * sharedX + b1;

        if (this.isInLine(sharedX, sharedY) && other.isInLine(sharedX, sharedY)) {
            return new Point(sharedX, sharedY);
        }
        return null;
    }

    /**
     * Finds the closest intersection point to the start of this line with a rectangle.
     *
     * @param rect the rectangle to find intersection with
     * @return the closest intersection point to the start of this line, null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null) {
            return null;
        }
        return this.start.closestPoint(intersectionPoints);
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }
}
