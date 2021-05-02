package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private boolean isPressed = true;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(key)) {
            if (!isAlreadyPressed()) {
                this.stop = true;
            }
        } else {
            this.isPressed = false;
        }
        animation.doOneFrame(d);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


    /**
     * isAlreadyPressed.
     * @return Boolean if is already pressed.
     */
    private boolean isAlreadyPressed() {
        return isPressed;
    }
}
