//Omer Mandalaoui 211695598
package geometry;

import java.util.ArrayList;

/**
 * The geometry.geometry.Rectangle class represents a rectangle in a 2D space defined
 * by an upper-left point, width, and height.
 * It provides methods to find intersection points with a line, retrieve dimensions,
 * and access rectangle sides.
 */
public class Rectangle {
    // fields
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a new rectangle with the specified upper-left point, width, and height.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line.
     *
     * @param line the line to find intersection points with
     * @return a list of intersection points between the rectangle and the line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        java.util.List<Line> sides = this.sides();
        for (Line side : sides) {
            Point interP = line.isIntersectingWith(side);
            if (interP != null) {
                intersectionPoints.add(interP);
            }
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the right side line of the rectangle.
     *
     * @return the right side line of the rectangle
     */
    public Line getRightLine() {
        double x1 = this.getUpperLeft().getX() + this.width;
        double y1 = this.getUpperLeft().getY();
        double x2 = this.getUpperLeft().getX() + this.width;
        double y2 = this.getUpperLeft().getY() + this.height;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the left side line of the rectangle.
     *
     * @return the left side line of the rectangle
     */
    public Line getLeftLine() {
        double x1 = this.getUpperLeft().getX();
        double y1 = this.getUpperLeft().getY();
        double x2 = this.getUpperLeft().getX();
        double y2 = this.getUpperLeft().getY() + this.height;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the upper side line of the rectangle.
     *
     * @return the upper side line of the rectangle
     */
    public Line getUpperLine() {
        double x1 = this.getUpperLeft().getX();
        double y1 = this.getUpperLeft().getY();
        double x2 = this.getUpperLeft().getX() + this.width;
        double y2 = this.getUpperLeft().getY();
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns the bottom side line of the rectangle.
     *
     * @return the bottom side line of the rectangle
     */
    public Line getBottomLine() {
        double x1 = this.getUpperLeft().getX();
        double y1 = this.getUpperLeft().getY() + this.height;
        double x2 = this.getUpperLeft().getX() + this.width;
        double y2 = this.getUpperLeft().getY() + this.height;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Returns a list of all sides (lines) of the rectangle.
     *
     * @return a list of all sides (lines) of the rectangle
     */
    public java.util.List<Line> sides() {
        java.util.List<Line> sides = new ArrayList<>();
        sides.add(this.getLeftLine());
        sides.add(this.getRightLine());
        sides.add(this.getBottomLine());
        sides.add(this.getUpperLine());
        return sides;
    }
}
