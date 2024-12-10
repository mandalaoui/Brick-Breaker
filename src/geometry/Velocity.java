//Omer Mandalaoui 211695598
package geometry;

/**
 * geometry.geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // fields
    private double dx;
    private double dy;

    /**
     * Constructs a geometry.geometry.Velocity with specified dx and dy values.
     *
     * @param dx the change in x per time unit
     * @param dy the change in y per time unit
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a geometry.geometry.Velocity instance from a given angle and speed.
     *
     * @param angle the direction of the velocity in degrees
     * @param speed the magnitude of the velocity
     * @return a new geometry.geometry.Velocity instance with calculated dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets the speed of the velocity vector.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Gets the angle of the velocity vector.
     *
     * @return the angle of the velocity in degrees
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * Applies the velocity to a given point and returns a new point.
     *
     * @param p the point to apply the velocity to
     * @return a new geometry.geometry.Point instance after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets the change in x (dx) component of the velocity.
     *
     * @return the dx component of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in y (dy) component of the velocity.
     *
     * @return the dy component of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the velocity components to the specified values.
     *
     * @param dx the new dx component of the velocity
     * @param dy the new dy component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Sets the velocity to match another geometry.geometry.Velocity object.
     *
     * @param vel the geometry.geometry.Velocity object to set this velocity to
     */
    public void setVelocity(Velocity vel) {
        this.dx = vel.getDx();
        this.dy = vel.getDy();
    }
}
