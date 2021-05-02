//ID:318948809
package interfaces;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * The interface Interfaces.Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();

    /**
     * Add to game.
     * add the object to the game
     *
     * @param g the object in the game
     */
    void addToGame(GameLevel g);
}