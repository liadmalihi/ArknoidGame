//ID:318948809
package sprites;

import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import geometry.Line;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprites.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * Instantiates a new Sprites.Block.
     *
     * @param rec   the rectangle
     * @param color the color
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * notifyHit.
     * called whenever a hit() occurs, and will notify all of the registered
     * Interfaces.HitListener objects by calling their hitEvent method
     *
     * @param hitter the ball in game
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
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
        Velocity v;
        Block b = new Block(this.rec, this.color);
        //if the point intersection with the down line
        if (line.intersectionWith(this.rec.lineDown()) != null) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //if the point intersection with the upper line
        if (line.intersectionWith(this.rec.lineUpper()) != null) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //if the point intersection with the right line
        if (line.intersectionWith(this.rec.lineRight()) != null) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //if the point intersection with the left line
        if (line.intersectionWith(this.rec.lineLeft()) != null) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return currentVelocity;
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
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add to game.
     * add the object to the game
     *
     * @param g the block in the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }
}
