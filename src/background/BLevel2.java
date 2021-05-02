package background;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type B level 2.
 */
public class BLevel2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(230, 200, 130));
        for (int i = 20; i < 750; i += 8) {
            d.drawLine(150, 150, i, 250);
        }
        d.fillCircle(150, 150, 70);
        d.setColor(new Color(250, 230, 100));
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(250, 250, 130));
        d.fillCircle(150, 150, 50);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
