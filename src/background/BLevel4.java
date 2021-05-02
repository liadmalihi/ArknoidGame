package background;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type B level 4.
 */
public class BLevel4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(00, 120, 250));
        d.fillRectangle(0, 0, 800, 600);
        for (int i = 100; i <= 170; i += 7) {
            d.setColor(Color.WHITE);
            d.drawLine(i, 400, i - 25, 600);
            d.drawLine(i + 500, 500, i + 500 - 25, 600);
        }
        d.setColor(new Color(220, 220, 200));
        d.fillCircle(100, 400, 20);
        d.fillCircle(120, 420, 20);
        d.fillCircle(600, 500, 20);
        d.fillCircle(620, 520, 20);
        d.setColor(new Color(200, 200, 200));
        d.fillCircle(130, 390, 25);
        d.fillCircle(630, 490, 25);
        d.setColor(new Color(150, 150, 170));
        d.fillCircle(160, 400, 30);
        d.fillCircle(660, 500, 30);
        d.fillCircle(145, 425, 15);
        d.fillCircle(645, 520, 15);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
