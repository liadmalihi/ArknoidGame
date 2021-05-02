package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *the function draw the animation.
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *the function returns true/false if to stop the animation.
     * @return the boolean true/false.
     */
    boolean shouldStop();
}