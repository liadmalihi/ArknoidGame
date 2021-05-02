package background;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type B level 3.
 */
public class BLevel3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(10, 150, 50));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.darkGray);
        d.fillRectangle(140, 200, 10, 200);
        d.fillRectangle(130, 400, 30, 50);
        d.setColor(Color.WHITE);
        d.fillRectangle(90, 440, 110, 200);
        d.setColor(Color.darkGray);
        for (int i = 90; i <= 200; i += 22) {
            d.fillRectangle(i, 440, 10, 200);
        }
        for (int i = 440; i <= 600; i += 33) {
            d.fillRectangle(90, i, 110, 7);
        }
        d.setColor(new Color(230, 200, 130));
        d.fillCircle(145, 190, 15);
        d.setColor(new Color(250, 0, 50));
        d.fillCircle(145, 190, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(145, 190, 3);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
