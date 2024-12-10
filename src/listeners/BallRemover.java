//Omer Mandalaoui 211695598
package listeners;

import game.Game;
import geometry.Ball;
import sprites.Block;

/**
 * The BallRemover class is responsible for removing balls from the game.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * Constructs a BallRemover.
     *
     * @param game the game instance
     * @param remainingBalls the counter for the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isDeathZone()) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}
