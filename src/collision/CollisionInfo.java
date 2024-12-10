//Omer Mandalaoui 211695598
package collision;

import geometry.Point;
import geometry.Rectangle;

/**
 * The collision.collision.CollisionInfo class represents information about a collision,
 * including the point of collision and the collidable object involved.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructs a collision.collision.CollisionInfo object with the given collision point and collidable object.
     *
     * @param collisionPoint  the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Calculates and returns the center of the collision point,
     * adjusted by the radius of the object.
     *
     * @param radius the radius of the object
     * @return the center point of the collision adjusted by the radius
     */
    public Point centerOfCollisinPoint(double radius) {
        double newX = this.collisionPoint.getX();
        double newY = this.collisionPoint.getY();
        Rectangle rect = this.collisionObject.getCollisionRectangle();
        if (rect.getUpperLine().isInLine(this.collisionPoint)) {
            newY -= radius;
        }
        if (rect.getBottomLine().isInLine(collisionPoint)) {
            newY += radius;
        }
        if (rect.getRightLine().isInLine(collisionPoint)) {
            newX += radius;
        }
        if (rect.getLeftLine().isInLine(collisionPoint)) {
            newX -= radius;
        }
        return new Point(newX, newY);
    }
}
