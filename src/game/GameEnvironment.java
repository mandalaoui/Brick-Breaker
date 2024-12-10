//Omer Mandalaoui 211695598
package game;

import collision.CollisionInfo;
import collision.Collidable;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The game.game.GameEnvironment class represents the environment where collisions occur.
 * It manages a collection of collidable objects and provides methods to add collidables
 * and find the closest collision point to a given trajectory.
 */
public class GameEnvironment {
    private final List<Collidable> collidables = new ArrayList<>();

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collision point along a given trajectory with any of the collidables.
     *
     * @param trajectory the trajectory (line) along which the collision is checked
     * @return collision.collision.CollisionInfo object containing information about the closest collision,
     * or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> allCollisionInfos = new ArrayList<>();
        List<Point> allCollisions = new ArrayList<>();
        Point collisionPoint;
        // Check for collision with each collidable
        for (Collidable c : collidables) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (collisionPoint != null) {
                allCollisionInfos.add(new CollisionInfo(collisionPoint, c));
                allCollisions.add(collisionPoint);
            }
        }
        // Find the closest collision point from all collisions
        Point closestPoint = trajectory.start().closestPoint(allCollisions);
        for (CollisionInfo cInfo : allCollisionInfos) {
            if (cInfo.collisionPoint().equals(closestPoint)) {
                return cInfo;
            }
        }
        return null;
    }
}
