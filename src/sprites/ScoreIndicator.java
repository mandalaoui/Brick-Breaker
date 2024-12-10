// Omer Mandalaoui 211695598
package sprites;

import listeners.Counter;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The {@code ScoreIndicator} class implements the {@code Sprite} interface.
 * It is responsible for displaying the current score of the game on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    /**
     * Constructs a {@code ScoreIndicator} with the specified score counter.
     *
     * @param score the counter that holds the current score to be displayed
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 20, "Score: " + this.score.getValue(), 20);
    }

    @Override
    public void timePassed() {
        // No implementation needed for this example
    }
}
