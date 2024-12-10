// Omer Mandalaoui 211695598
package sprites;

import biuoop.DrawSurface;
import collision.Collidable;
import game.Game;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The sprites.sprites.Block class represents a block with a rectangle shape and color.
 * It includes methods to draw the block, handle collisions, and add the block to the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners;
    private boolean isFrame;
    private boolean isDeathZone;
    /**
     * Constructor to create a block with a given rectangle and color.
     *
     * @param rect  the rectangle shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.isFrame = false;
        this.isDeathZone = false;
    }
    /**
     * Constructs a Block with the specified rectangle, color, and frame status.
     *
     * @param rect  the Rectangle representing the shape and position of the block
     * @param color the color of the block
     * @param frame true if the block is a frame, false otherwise
     */
    public Block(Rectangle rect, Color color, boolean frame) {
        this(rect, color);
        this.isFrame = frame;
    }
    /**
     * Constructs a Block with the specified rectangle, color, frame status, and death zone status.
     *
     * @param rect       the Rectangle representing the shape and position of the block
     * @param color      the color of the block
     * @param frame      true if the block is a frame, false otherwise
     * @param deathZone  true if the block is a death zone, false otherwise
     */
    public Block(Rectangle rect, Color color, boolean frame, boolean deathZone) {
        this(rect, color, frame);
        this.isDeathZone = deathZone;
    }
    /**
     * Gets the list of hit listeners attached to the block.
     *
     * @return a list of HitListener objects
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }
    /**
     * Checks if the block is a frame.
     *
     * @return true if the block is a frame, false otherwise
     */
    public boolean isFrame() {
        return this.isFrame;
    }
    /**
     * Checks if the block is a death zone.
     *
     * @return true if the block is a death zone, false otherwise
     */
    public boolean isDeathZone() {
        return this.isDeathZone;
    }

    // Gets the collision rectangle of the block.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    // Handles the hit event when a ball collides with the block.
    // It changes the ball's velocity based on the collision point.
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check if the collision point is on the upper line of the block
        if (this.rectangle.getUpperLine().isInLine(collisionPoint)) {
            dy = -Math.abs(dy);
        }
        // Check if the collision point is on the bottom line of the block
        if (this.rectangle.getBottomLine().isInLine(collisionPoint)) {
            dy = Math.abs(dy);
        }
        // Check if the collision point is on the right line of the block
        if (this.rectangle.getRightLine().isInLine(collisionPoint)) {
            dx = Math.abs(dx);
        }
        // Check if the collision point is on the left line of the block
        if (this.rectangle.getLeftLine().isInLine(collisionPoint)) {
            dx = -Math.abs(dx);
        }
        if (this.isDeathZone) {
            this.notifyHit(hitter);
        } else if (!ballColorMatch(hitter)) {
            if (hitter != null && !this.isFrame) {
                hitter.setColor(this.color);
            }
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }

    // Draws the block on the given DrawSurface.
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        // Draw the filled rectangle with the block's color
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);

        // Draw the rectangle border in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    // Notifies the block that time has passed. This method currently does nothing.
    @Override
    public void timePassed() {
        // Currently no action is needed on timePassed for blocks
    }

    /**
     * Adds the block to the given game.
     *
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the color of the given ball matches the color of this object.
     *
     * <p>This method compares the color of the provided {@link Ball} object with the color of this object
     * to determine if they are the same. It returns {@code true} if the colors match and {@code false} otherwise.</p>
     *
     * @param ball the {@link Ball} object whose color is to be compared with this object's color
     * @return {@code true} if the color of the provided ball matches this object's color, {@code false} otherwise
     * @throws NullPointerException if the provided {@code ball} is {@code null}
     */
    public Boolean ballColorMatch(Ball ball) {
        if (ball == null) {
            return false;
        }
        return this.color.equals(ball.getColor());
    }
    /**
     * Removes this sprite and collidable object from the given game.
     *
     * <p>This method removes the current object from both the sprite collection and the collidable objects
     * in the specified {@link Game} instance. It ensures that this object is no longer part of the game's
     * visual elements and collision detection mechanisms.</p>
     *
     * @param game the {@link Game} instance from which this object will be removed
     * @throws NullPointerException if the provided {@code game} is {@code null}
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * Notifies all registered hit listeners about a hit event.
     * <p>
     * This method makes a copy of the current list of hit listeners before iterating over them.
     * Each listener is notified of the hit event, with the current block and the hitting ball provided as parameters.
     * </p>
     *
     * @param hitter the Ball object that caused the hit event
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            hitListeners.add(hl);
        }
    }
    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
