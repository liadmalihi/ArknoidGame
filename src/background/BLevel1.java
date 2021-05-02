package background;

import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type B level 1.
 */
public class BLevel1 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(50, 50, 250));
        d.drawCircle(400, 210, 50);
        d.drawCircle(400, 210, 80);
        d.drawCircle(400, 210, 110);
        d.drawLine(400, 80, 400, 190);
        d.drawLine(400, 230, 400, 340);
        d.drawLine(270, 210, 380, 210);
        d.drawLine(420, 210, 530, 210);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
