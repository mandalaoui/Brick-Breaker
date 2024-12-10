// Omer Mandalaoui 211695598
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision.Collidable;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import listeners.Counter;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.SpriteCollection;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;

import java.awt.Color;

/**
 * The game.game.Game class initializes and runs the game loop.
 * It manages the game elements including sprites, collidables, GUI, and game environment.
 */
public class Game {
    private SpriteCollection sprites; // Collection of all sprites in the game
    private GameEnvironment environment; // game.game.Game environment containing all collidable objects
    private GUI gui; // Graphical user interface for drawing on screen
    private Sleeper sleeper; // Sleeper for controlling frame rate
    private biuoop.KeyboardSensor keyboard; // Keyboard sensor for user input
    private Paddle paddle; // The game's paddle object
    private Counter blocksCounter;
    private Counter ballsCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;

    /**
     * Adds a collidable object to the game's environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game's collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable object to from game's environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Gets the counter for the number of blocks.
     *
     * @return the blocks counter
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }
    /**
     * Gets the counter for the score.
     *
     * @return the score counter
     */
    public Counter getScore() {
        return score;
    }
    /**
     * Gets the counter for the number of balls.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * Removes a sprite object from the game's collection.
     *
     * @param s the sprite object to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Runs the game loop.
     * Continuously updates game elements, handles user input, and manages frame rate.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.blocksCounter.getValue() > 0 && this.ballsCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);

            // Handle paddle movement based on user input
            if (this.keyboard != null) {
                if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
                    this.paddle.moveLeft();
                }
                if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
                    this.paddle.moveRight();
                }
            }

            // Update all sprites in the game
            this.sprites.notifyAllTimePassed();

            // Control frame rate
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondsLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondsLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondsLeftToSleep);
            }
        }
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
        }
        System.out.println("score: " + this.score.getValue());
        this.gui.close();
    }

    /**
     * Initializes the game by creating game elements (blocks, balls, paddle) and adding them to the game.
     * Also initializes the GUI and other necessary components.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("", 800, 600);
        this.sleeper = new Sleeper();
        this.keyboard = gui.getKeyboardSensor();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = new Counter();

        // Create paddle and add it to the game
        this.paddle = new Paddle(new Block(new Rectangle(new Point(400, 560), 80, 20), Color.BLUE));
        this.paddle.addToGame(this);

        // Create borders and blocks and add them to the game environment
        Block leftBorder = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY, true);
        Block rightBorder = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY, true);
        Block upperBorder = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY, true);
        Block deathZone = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY, true, true);

        // Create the blocks
        Block[] blocks = new Block[57];
        for (int i = 0; i < 57; i++) {
            if (i < 12) {
                blocks[i] = new Block(new Rectangle(new Point(180 + i * 50, 150), 50, 20), Color.RED);
            } else if (i < 23) {
                blocks[i] = new Block(new Rectangle(new Point(230 + (i - 12) * 50, 170), 50, 20), Color.GREEN);
            } else if (i < 33) {
                blocks[i] = new Block(new Rectangle(new Point(280 + (i - 23) * 50, 190), 50, 20), Color.YELLOW);
            } else if (i < 42) {
                blocks[i] = new Block(new Rectangle(new Point(330 + (i - 33) * 50, 210), 50, 20), Color.MAGENTA);
            } else if (i < 50) {
                blocks[i] = new Block(new Rectangle(new Point(380 + (i - 42) * 50, 230), 50, 20), Color.ORANGE);
            } else {
                blocks[i] = new Block(new Rectangle(new Point(430 + (i - 50) * 50, 250), 50, 20), Color.GRAY);
            }
        }
        this.blocksCounter.increase(57);
        this.blockRemover = new BlockRemover(this, this.getBlocksCounter());
        // Add borders and blocks to the game
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        upperBorder.addToGame(this);
        deathZone.addToGame(this);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.getScore());
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(this.blockRemover);
            b.addHitListener(scoreListener);
        }
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.getScore());
        this.sprites.addSprite(scoreIndicator);
        // Set the start point of balls in the middle of the screen
        Point startPoint = new Point(400, 400);

        // Create balls and add them to the game
        Ball ball1 = new Ball(startPoint, 5, java.awt.Color.BLACK, this.environment);
        Ball ball2 = new Ball(startPoint, 5, java.awt.Color.BLACK, this.environment);
        Ball ball3 = new Ball(startPoint, 5, java.awt.Color.BLACK, this.environment);
        // Set initial velocities for balls
        Velocity v1 = Velocity.fromAngleAndSpeed(330, 7);
        ball1.setVelocity(v1);
        Velocity v2 = Velocity.fromAngleAndSpeed(210, 7);
        ball2.setVelocity(v2);
        Velocity v3 = Velocity.fromAngleAndSpeed(260, 7);
        ball3.setVelocity(v3);
        // Set all the balls in an array
        Ball[] balls = new Ball[3];
        balls[0] = ball1;
        balls[1] = ball2;
        balls[2] = ball3;
        // Add the balls to the game
        this.ballRemover = new BallRemover(this, this.getBallsCounter());
        for (Ball b : balls) {
            b.addToGame(this);
            this.ballsCounter.increase(1);
        }
        deathZone.addHitListener(ballRemover);

    }


    /**
     * Main method to run the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
