//Omer Mandalaoui 211695598
package listeners;

import geometry.Ball;
import sprites.Block;
/**
 * The HitListener interface should be implemented by any class that wants to be notified
 * of hit events on blocks. When a block is hit, the hitEvent method is called.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}

