//ID:318948809
package sprites;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitlistener.Counter;
import interfaces.Sprite;
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter counterIndicator;
    private GameLevel g;


    /**
     * Instantiates a new Score indicator.
     *
     * @param counterIndicator the counter indicator
     * @param game             the game
     */
    public ScoreIndicator(Counter counterIndicator, GameLevel game) {
        this.counterIndicator = counterIndicator;
        this.g = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle r4 = new Rectangle(new Point(0, 0), 800, 20);
        d.setColor(Color.lightGray);
        d.fillRectangle((int) r4.getUpperLeft().getX(), (int) r4.getUpperLeft().getY(),
                (int) r4.getWidth(), (int) r4.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) r4.getUpperLeft().getX(), (int) r4.getUpperLeft().getY(),
                (int) r4.getWidth(), (int) r4.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(370, 20, "Score:" + counterIndicator.getValue(), 20);
        d.drawText(640, 17, "Level Name:" + g.getLevel().levelName(), 15);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
