//Omer Mandalaoui 211695598
package collision;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The collision.collision.Collidable interface represents objects that can be collided with.
 * It includes methods to get the collision shape and handle collisions.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the given collision point
     * with the given velocity. It returns the new velocity expected after the hit
     * (based on the force the object inflicted).
     *
     * @param hitter          the {@link Ball} object that caused the collision
     * @param collisionPoint  the point where the collision occurs
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
