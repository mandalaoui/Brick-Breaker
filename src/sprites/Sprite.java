//Omer Mandalaoui 211695598
package sprites;
import biuoop.DrawSurface;

/**
 * The sprites.sprites.Sprite interface represents game objects that can be drawn to the screen
 * and can be notified that time has passed.
 */
public interface Sprite {
    /**
     * Draws the sprite to the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}
