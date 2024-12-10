//Omer Mandalaoui 211695598
package geometry;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision.CollisionInfo;
import sprites.Block;
import sprites.Sprite;
import game.Game;
import game.GameEnvironment;
import java.awt.Color;


/**
 * The geometry.Ball class represents a ball with a center point, radius, color, and velocity.
 * It includes methods to draw the ball, move the ball, and handle collisions with borders.
 */
public class Ball implements Sprite {
    // fields
    private Point center;
    private final int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;

    /**
     * Constructor to create a ball with a given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param gameEnvironment the game environment the ball interacts with
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.color = color;
        this.radius = r;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructor to create a ball with given x and y coordinates, radius, and color.
     *
     * @param x      the x coordinate of the center point
     * @param y      the y coordinate of the center point
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param gameEnvironment the game environment the ball interacts with
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.color = color;
        this.radius = r;
        this.gameEnvironment = gameEnvironment;
    }

    // accessors
    /**
     * Gets the x coordinate of the ball's center.
     *
     * @return the x coordinate of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y coordinate of the ball's center.
     *
     * @return the y coordinate of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param c the new color of the ball
     */
    public void setColor(Color c) {
        this.color = c;
    }

    //Draws the ball on the given DrawSurface.
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize()); // x, y, r
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy components.
     *
     * @param dx the change in x per time unit
     * @param dy the change in y per time unit
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the current velocity of the ball.
     *
     * @return the current velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step, considering the window borders.
     */
    public void moveOneStep() {
        // Get the current velocity
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        // Calculate new position
        double newX = this.center.getX() + dx;
        double newY = this.center.getY() + dy;
        // Create the trajectory line from current position to next position
        Line trajectory = new Line(this.center, new Point(newX, newY));

        // Get the closest collision point and collidable object
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo != null) {
            // Move to the closest collision point
            Point collisionPoint = collisionInfo.collisionPoint();
            this.center.setPoint(collisionInfo.centerOfCollisinPoint(this.radius));

            // Calculate new velocity after collision
            this.velocity.setVelocity(collisionInfo.collisionObject().hit(this, collisionPoint, this.velocity));
        } else {
            // No collision, move to the next position
            this.center = new Point(newX, newY);
        }
    }

    // Notifies the ball that time has passed and it should move one step.
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the given game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Removes the ball from the given game.
     *
     * @param game the game to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
    /**
     * The main method to run the geometry.Ball animation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new Sleeper();
        GameEnvironment game = new GameEnvironment();

        Ball ball = new Ball(100, 300, 10, java.awt.Color.BLACK, game);

        Block leftBorder = new Block(new Rectangle(new Point(-1, 0), 1, 600), Color.BLACK);
        Block rightBorder = new Block(new Rectangle(new Point(800, 0), 1, 600), Color.BLACK);
        Block upperBorder = new Block(new Rectangle(new Point(0, -1), 800, 1), Color.BLACK);
        Block bottomBorder = new Block(new Rectangle(new Point(0, 600), 800, 1), Color.BLACK);

        Block[] blocks = new Block[5];
        blocks[0] = new Block(new Rectangle(new Point(20, 20), 100, 40), Color.RED);
        blocks[1] = new Block(new Rectangle(new Point(150, 80), 100, 40), Color.RED);
        blocks[2] = new Block(new Rectangle(new Point(280, 140), 100, 40), Color.RED);
        blocks[3] = new Block(new Rectangle(new Point(410, 200), 100, 40), Color.RED);
        blocks[4] = new Block(new Rectangle(new Point(540, 260), 100, 40), Color.RED);

        game.addCollidable(leftBorder);
        game.addCollidable(rightBorder);
        game.addCollidable(upperBorder);
        game.addCollidable(bottomBorder);
        for (Block b : blocks) {
            game.addCollidable(b);
        }

        Velocity v = Velocity.fromAngleAndSpeed(70, 20);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Block b : blocks) {
                b.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

}
