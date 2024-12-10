//Omer Mandalaoui 211695598
package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The sprites.sprites.SpriteCollection class represents a collection of sprites.
 * It manages operations such as adding sprites, notifying all sprites of time passage,
 * and drawing all sprites on a DrawSurface.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> spriteCollection = new ArrayList<>();
    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        spriteCollection.add(s);
    }
    /**
     * Removes a sprite to from collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        spriteCollection.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method calls the timePassed() method on each sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spriteCollection);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the specified DrawSurface.
     * This method calls the drawOn(d) method on each sprite.
     *
     * @param d the DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<>(spriteCollection);
        for (Sprite sprite : spritesCopy) {
            sprite.drawOn(d);
        }
    }
}
