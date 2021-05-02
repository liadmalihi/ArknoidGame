package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import geometry.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private long millisecondsPerFrame;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.millisecondsPerFrame = (long) (1000 * this.numOfSeconds / (countFrom + 1));
    }

    //The CountdownAnimation will display the given gameScreen,
    // for numOfSeconds seconds, and on top of them it will show
    // a countdown from countFrom back to 1, where each number will
    // appear on the screen for (numOfSeconds / countFrom) seconds, before
    // it is replaced with the next one.
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        if (countFrom == 3) {
            d.setColor(Color.magenta);
            d.fillCircle(d.getWidth() / 2 + 30, d.getHeight() / 2 - 30, 50);
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 100);
        }
        if (countFrom == 2) {
            d.setColor(Color.magenta);
            d.fillCircle(d.getWidth() / 2 + 30, d.getHeight() / 2 - 30, 50);
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 100);
            sleeper.sleepFor(millisecondsPerFrame);
        }
        if (countFrom == 1) {
            d.setColor(Color.magenta);
            d.fillCircle(d.getWidth() / 2 + 30, d.getHeight() / 2 - 30, 50);
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 100);
            sleeper.sleepFor(millisecondsPerFrame);
        }
        if (countFrom < 1) {
            d.setColor(Color.magenta);
            d.fillCircle(d.getWidth() / 2 + 30, d.getHeight() / 2 - 30, 50);
            d.setColor(Color.red);
            d.drawText(390, 290, "GO", 50);
            sleeper.sleepFor(millisecondsPerFrame);
        }
        if (countFrom < 0) {
            this.stop = true;
        }
        countFrom--;

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}