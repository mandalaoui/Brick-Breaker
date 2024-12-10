//Omer Mandalaoui 211695598
package listeners;

import geometry.Ball;
import sprites.Block;
/**
 * The ScoreTrackingListener class implements the HitListener interface to keep track of the player's score.
 * It listens for hit events and updates the score accordingly.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * Constructs a ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter the Counter object used to track the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Gets the current score counter.
     *
     * @return the Counter object used to track the current score
     */
    public final Counter getCurrentScore() {
        return currentScore;
    }
    // This method is called whenever the beingHit object is hit. It increases the score by 5 points if
    // the hit object is not a frame.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.isFrame()) {
            this.currentScore.increase(5);
        }
    }
}