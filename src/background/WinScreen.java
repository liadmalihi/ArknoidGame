package background;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import hitlistener.Counter;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new Win screen.
     *
     * @param k the k
     * @param s the s
     */
    public WinScreen(KeyboardSensor k, Counter s) {
        this.keyboard = k;
        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(200, 0, 200));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(230, 250, 0));
        d.fillCircle(400, 300, 250);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 10);
        d.fillCircle(300, 200, 30);
        d.fillCircle(500, 200, 30);
        d.fillRectangle(300, 400, 10, 20);
        d.fillRectangle(500, 400, 10, 20);
        d.fillRectangle(300, 420, 210, 10);
        d.setColor(new Color(100, 100, 200));
        d.fillCircle(400, 550, 20);
        d.setColor(new Color(100, 100, 250));
        d.fillCircle(370, 540, 15);
        d.fillCircle(370, 560, 15);
        d.fillCircle(430, 540, 15);
        d.fillCircle(430, 560, 15);
        d.setColor(Color.CYAN);
        d.drawText(20, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 60);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
