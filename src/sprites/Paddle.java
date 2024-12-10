//Omer Mandalaoui 211695598
package sprites;
import biuoop.DrawSurface;

import collision.Collidable;
import geometry.Ball;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import game.Game;
/**
 * The sprites.sprites.Paddle class represents a paddle in a game, which is controlled by the player.
 * It implements the sprites.sprites.Sprite and collision.collision.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard; // Keyboard sensor to control paddle movement
    private final Block paddle; // The block representing the paddle

    /**
     * Constructor for sprites.sprites.Paddle class.
     *
     * @param paddle the block representing the paddle
     */
    public Paddle(Block paddle) {
        this.paddle = paddle;
    }

    /**
     * Moves the paddle left by a fixed amount.
     * Wraps around to the right side of the screen if reaching the left edge.
     */
    public void moveLeft() {
        Point upperLeft = this.paddle.getCollisionRectangle().getUpperLeft();
        if (upperLeft.getX() <= 20) {
            this.paddle.getCollisionRectangle().getUpperLeft().setPoint(new Point(
                    780 - paddle.getCollisionRectangle().getWidth(), upperLeft.getY()));
        } else {
            this.paddle.getCollisionRectangle().getUpperLeft().setPoint(new Point(upperLeft.getX() - 8,
                    upperLeft.getY()));
        }
    }

    /**
     * Moves the paddle right by a fixed amount.
     * Wraps around to the left side of the screen if reaching the right edge.
     */
    public void moveRight() {
        Point upperLeft = this.paddle.getCollisionRectangle().getUpperLeft();
        if (upperLeft.getX() + paddle.getCollisionRectangle().getWidth() >= 780) {
            this.paddle.getCollisionRectangle().getUpperLeft().setPoint(new Point(20, upperLeft.getY()));
        } else {
            this.paddle.getCollisionRectangle().getUpperLeft().setPoint(
                    new Point(upperLeft.getX() + 8, upperLeft.getY()));
        }
    }
    // Notifies the paddle that time has passed.
    // This method delegates to the block representing the paddle.
    @Override
    public void timePassed() {
        this.paddle.timePassed();
    }

    // Draws the paddle on the provided DrawSurface.
    // This method delegates to the block representing the paddle.
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }
    /**
     * Returns the keyboard sensor associated with this paddle.
     *
     * @return the keyboard sensor
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    // Returns the collision rectangle of the paddle.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    private static final double EPSILON = 1e-7;

    /**
     * Checks if a value is between two other values.
     *
     * @param a      the value to check
     * @param first  the first bound
     * @param second the second bound
     * @return true if the value is between the bounds, false otherwise
     */
    public boolean isBetween(double a, double first, double second) {
        return (a >= Math.min(first, second) - EPSILON && a <= Math.max(first, second) + EPSILON);
    }
    /**
     * The {@code PaddleRegion} enum represents different regions of the paddle.
     * Each region determines the angle at which the ball will bounce off the paddle.
     */
    public enum PaddleRegion {
        REGION1(300),  // Bounce angle of 300 degrees (-60 degrees)
        REGION2(330),  // Bounce angle of 330 degrees (a little to the left)
        REGION3(0),    // Horizontal direction unchanged, vertical direction changes
        REGION4(-30),  // Bounce angle of -30 degrees (a little to the right)
        REGION5(-60);  // Bounce angle of -60 degrees (a lot to the right)

        private final double angle;
        /**
         * Constructs a {@code PaddleRegion} with the specified bounce angle.
         *
         * @param angle the bounce angle in degrees for this region
         */
        PaddleRegion(double angle) {
            this.angle = angle;
        }
        /**
         * Gets the bounce angle for this paddle region.
         *
         * @return the bounce angle in degrees
         */
        public double getAngle() {
            return this.angle;
        }
    }
    // Determines the new velocity of the ball after hitting the paddle
    // Check if the collision point is on the upper line of the block
        /* if so:
         The paddle is divided into 5 equally spaced regions. The behavior of the ball's bounce depends on
         which region it hits:
         - Region 1 (leftmost): Bounce angle of 300 degrees (-60 degrees).
         - Region 2: Bounce angle of 330 degrees (a little to the left).
         - Region 3 (middle): Horizontal direction remains unchanged, vertical direction changes.
         - Region 4: Bounce angle of 30 degrees (a little to the right).
         - Region 5 (rightmost): Bounce angle of 60 degrees (a lot to the right).
         */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.getCollisionRectangle().getUpperLine().isInLine(collisionPoint)) {
            double space = this.getCollisionRectangle().getWidth() / 5;
            double x = collisionPoint.getX();
            PaddleRegion region;
            if (isBetween(x, this.getCollisionRectangle().getUpperLeft().getX(),
                    this.getCollisionRectangle().getUpperLeft().getX() + space)) {
                region = PaddleRegion.REGION1;
            } else if (isBetween(x, this.getCollisionRectangle().getUpperLeft().getX()
                    + space, this.getCollisionRectangle().getUpperLeft().getX() + 2 * space)) {
                region = PaddleRegion.REGION2;
            } else if (isBetween(x, this.getCollisionRectangle().getUpperLeft().getX()
                    + 2 * space, this.getCollisionRectangle().getUpperLeft().getX() + 3 * space)) {
                region = PaddleRegion.REGION3;
            } else if (isBetween(x, this.getCollisionRectangle().getUpperLeft().getX()
                    + 3 * space, this.getCollisionRectangle().getUpperLeft().getX() + 4 * space)) {
                region = PaddleRegion.REGION4;
            } else {
                region = PaddleRegion.REGION5;
            }
            if (region == PaddleRegion.REGION3) {
                return this.paddle.hit(null, collisionPoint, currentVelocity);
            } else {
                return Velocity.fromAngleAndSpeed(region.getAngle(), currentVelocity.getSpeed());
            }
        }

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check if the collision point is on the bottom line of the block
        if (this.getCollisionRectangle().getBottomLine().isInLine(collisionPoint)) {
            dy = Math.abs(dy);
        }
        // Check if the collision point is on the right line of the block
        if (this.getCollisionRectangle().getRightLine().isInLine(collisionPoint)) {
            dx = Math.abs(dx);
        }
        // Check if the collision point is on the left line of the block
        if (this.getCollisionRectangle().getLeftLine().isInLine(collisionPoint)) {
            dx = -Math.abs(dx);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Adds this paddle to the game.
     * Registers the paddle as a sprite and collidable object in the game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
