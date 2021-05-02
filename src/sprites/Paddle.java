//ID:318948809
package sprites;

import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import game.GameEnvironment;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Sprites.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The Paddle height.
     */
    static final int PADDLE_HEIGHT = 10;
    /**
     * The Screen width.
     */
    static final int SCREEN_WIDTH = 800;
    /**
     * The Block width.
     */
    static final int BLOCK_WIDTH = 30;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Color color;
    private GameEnvironment gE;
    private int paddleSpeed;
    private int width;

    /**
     * Instantiates a new Sprites.Paddle.
     *
     * @param keyboard the keyboard
     * @param move     the move
     * @param width    the width
     */
    public Paddle(KeyboardSensor keyboard, int move, int width) {
        this.rec = new Rectangle(new Point((SCREEN_WIDTH - width) / 2, 570), width, PADDLE_HEIGHT);
        this.color = Color.ORANGE;
        this.keyboard = keyboard;
        paddleSpeed = move;
        this.width = width;
    }

    /**
     * Move paddle to the middle.
     */
    public void movePaddleToTheMiddle() {
        this.rec = new Rectangle((new Point((SCREEN_WIDTH - width) / 2, 570)), width, PADDLE_HEIGHT);
    }

    /**
     * Move left.
     *
     * @param move the move
     */
    public void moveLeft(int move) {
        double x = rec.getUpperLeft().getX() - move;
        double y = rec.getUpperLeft().getY();
        this.rec = new Rectangle(new Point(x, y), width, PADDLE_HEIGHT);
    }

    /**
     * Move right.
     *
     * @param move the move
     */
    public void moveRight(int move) {
        double x = rec.getUpperLeft().getX() + move;
        double y = rec.getUpperLeft().getY();
        this.rec = new Rectangle(new Point(x, y), width, PADDLE_HEIGHT);
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && rec.getUpperLeft().getX()
                <= SCREEN_WIDTH - width - BLOCK_WIDTH - paddleSpeed) {
            this.moveRight(paddleSpeed);
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && rec.getUpperLeft().getX() >= paddleSpeed + BLOCK_WIDTH) {
            this.moveLeft(paddleSpeed);

        }
    }

    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the draw sur face
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle-the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }


    /**
     * Hit velocity.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line line = new Line(collisionPoint, collisionPoint);
        Point p;
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        //if the point intersection with the down line
        if (line.intersectionWith(this.rec.lineDown()) != null) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //if the point intersection with the upper line
        if (line.intersectionWith(this.rec.lineUpper()) != null) {
            p = line.intersectionWith(this.rec.lineUpper());
            //If he hits the first area - in the first fifth
            if (p.getX() < rec.getUpperLeft().getX() + (width / 5)) {
                return Velocity.fromAngleAndSpeed(300, speed);
            }
            //If he hits the second area - in the second fifth
            if (p.getX() >= rec.getUpperLeft().getX() + (width / 5)
                    && p.getX() < rec.getUpperLeft().getX() + 2 * (width / 5)) {
                return Velocity.fromAngleAndSpeed(330, speed);
            }
            //If he hits the middle of area
            if (p.getX() >= rec.getUpperLeft().getX() + 2 * (width / 5)
                    && p.getX() < rec.getUpperLeft().getX() + 3 * (width / 5)) {
                return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            //If he hits the fourth area - the fourth fifth
            if (p.getX() >= rec.getUpperLeft().getX() + 3 * (width / 5)
                    && p.getX() < rec.getUpperLeft().getX() + 4 * (width / 5)) {
                return Velocity.fromAngleAndSpeed(30, speed);
            }
            //If he hits the fifth area - the last fifth
            if (p.getX() >= rec.getUpperLeft().getX() + 4 * (width / 5)) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        //if the point intersection with the right line
        if (line.intersectionWith(this.rec.lineRight()) != null) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //if the point intersection with the left line
        if (line.intersectionWith(this.rec.lineLeft()) != null) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param g the paddle in the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}