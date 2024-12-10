// Omer Mandalaoui 211695598

import game.Game;

/**
 * The game.Ass3Game class is the entry point for the game.
 */
public class Ass5Game {

    /**
     * The main method creates a new game, initializes it, and starts the game loop.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();

    }
}
