//Omer Mandalaoui 211695598
package listeners;

import game.Game;
import geometry.Ball;
import sprites.Block;

/**
 * A BlockRemover is responsible for removing blocks from the game and keeping count
 * of the number of blocks that remain. It implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game            the game instance
     * @param remainingBlocks the counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.isFrame()) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
            // Remove only this listener
            beingHit.removeHitListener(this);
        }
    }
}