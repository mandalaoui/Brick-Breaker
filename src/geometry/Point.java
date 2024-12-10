//Omer Mandalaoui 211695598
package geometry;
/**
 * The geometry.geometry.Point class represents a point in a 2D space.
 * It contains methods to calculate the distance to another point
 * and to check if two points are equal.
 */
public class Point {
    // fields
    private double x;
    private double y;

    /**
     * Constructs a geometry.geometry.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance from this point to another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Finds the closest point to this point from a list of points.
     *
     * @param points a list of points to find the closest one from
     * @return the closest point to this point from the list
     */
    public Point closestPoint(java.util.List<Point> points) {
        if (points == null || points.isEmpty() || points.get(0) == null) {
            return null;
        }

        double minDistance = this.distance(points.get(0));
        Point closestPoint = points.get(0);

        for (Point p : points) {
            double distance = this.distance(p);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = p;
            }
        }

        return closestPoint;
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point to compare with
     * @return true if the points have the same coordinates, false otherwise
     */
    public boolean equals(Point other) {
        if (other != null) {
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point to a new value.
     *
     * @param x the new x-coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point to a new value.
     *
     * @param y the new y-coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the coordinates of this point to the coordinates of another point.
     *
     * @param newP the new point with coordinates to set
     */
    public void setPoint(Point newP) {
        this.setX(newP.getX());
        this.setY(newP.getY());
    }
}
