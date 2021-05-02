package background;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import hitlistener.Counter;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Lose screen.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new Lose screen.
     *
     * @param k the k
     * @param s the s
     */
    public LoseScreen(KeyboardSensor k, Counter s) {
        this.keyboard = k;
        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(230, 250, 0));
        d.fillCircle(400, 300, 250);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 10);
        d.fillCircle(300, 200, 30);
        d.fillCircle(500, 200, 30);
        d.fillRectangle(300, 400, 10, 30);
        d.fillRectangle(500, 400, 10, 30);
        d.fillRectangle(300, 400, 210, 10);
        d.setColor(Color.white);
        d.drawText(20, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}