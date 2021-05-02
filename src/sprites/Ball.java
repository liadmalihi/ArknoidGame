//ID:318948809
package sprites;

import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import geometry.Line;
import geometry.CollisionInfo;
import game.GameEnvironment;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Sprites.Ball.
 */
public class Ball implements Sprite {
    /**
     * The Center.
     */
    private Point center;
    /**
     * The Radius.
     */
    private int radius;
    /**
     * The Color.
     */
    private java.awt.Color color;
    /**
     * The Geometry.Velocity.
     */
    private Velocity velocity;

    private GameEnvironment gE;

    /**
     * Instantiates a new Sprites.Ball.
     * The function accepts variables and puts them into a ballץ
     *
     * @param x      the x-of point
     * @param y      the y-of point
     * @param radius the radius
     * @param color  the color
     */
// constructor
    public Ball(int x, int y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * Instantiates a new Sprites.Ball.
     * The function accepts variables and puts them into a ball
     *
     * @param center the center-point
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Instantiates a new Sprites.Ball.
     * The function accepts variables and puts them into a ball
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     * @param game   the game
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gE = game;
    }

    /**
     * Gets x.
     * the function return the value x of point of ball
     *
     * @return the x-of point
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     * the function return the value y of point of ball
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     * the function return the size of radius of ball
     *
     * @return the size-radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     * the function return the color of ball
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public GameEnvironment getGame() {
        return this.gE;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(GameEnvironment game) {
        this.gE = game;
    }

    /**
     * Sets velocity.
     * The function accepts 2 values, and updates the velocity
     *
     * @param dx the dx of velocity
     * @param dy the dy of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     * The function return the velocity
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets velocity.
     * The function accepts velocity, and updates the velocity
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Move one step.
     * The function checks if the ball has points of contact with the object,
     * and changes the direction of movement of the ball when it meets the nearest point
     */
    public void moveOneStep() {
        Velocity velocity1;
        Line line = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo c = gE.getClosestCollision(line);
        //If there is no meeting point
        if (c == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            velocity = c.collisionObject().hit(this, c.collisionPoint(), velocity);
        }
    }

    /**
     * Add to game.
     * add the ball to the game
     *
     * @param g the ball in the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Move one step.
     * The function accepts the boundary of the board, and changes the speed and direction when
     * the ball reaches the boundary of the board.
     * By calling for a function moveOneStep at the end
     *
     * @param width  the width
     * @param height the height
     */
    public void moveOneStep(double width, double height) {
        if (this.center.getX() + radius + this.velocity.getDx() > width
                || this.getX() - radius + this.velocity.getDx() < 0) {
            setVelocity(-velocity.getDx(), velocity.getDy());
        }
        if (this.center.getY() + radius + this.velocity.getDy() > height
                || this.getY() - radius + this.velocity.getDy() < 0) {
            setVelocity(velocity.getDx(), -velocity.getDy());
        }
        moveOneStep();
    }

    /**
     * Move one step.
     * The function accepts the boundary of the board, and changes the speed and direction when
     * the ball reaches the boundary of the board.
     * By calling for a function moveOneStep at the end
     *
     * @param x      the x-The starting point of the board
     * @param y      the y-The starting point of the board
     * @param width  the width-The end point of the board
     * @param height the height-The end point of the board
     */
    public void moveOneStep(int x, int y, int width, int height) {
        //Checks whether the value of X exceeds the boundary of the board
        if (this.center.getX() + radius + this.velocity.getDx() >= width
                || this.getX() - radius + this.velocity.getDx() <= x) {
            setVelocity(-velocity.getDx(), velocity.getDy());
        }
        //Checks whether the value of Y exceeds the boundary of the board
        if (this.center.getY() + radius + this.velocity.getDy() >= height
                || this.getY() - radius + this.velocity.getDy() <= y) {
            setVelocity(velocity.getDx(), -velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Draw on.
     * the function draw the ball on the given DrawSurface
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Remove from game.
     * remove the sprite from the game
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
