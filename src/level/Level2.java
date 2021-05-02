package level;

import background.BLevel2;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            v.add(Velocity.fromAngleAndSpeed(x + 15, 10));
            x += 15;
        }
        x = 360;
        for (int i = numberOfBalls() / 2; i < numberOfBalls(); i++) {
            v.add(Velocity.fromAngleAndSpeed(x - 15, 10));
            x -= 15;
        }
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        String s = "Wide Easy";
        return s;
    }

    @Override
    public Sprite getBackground() {
        return new BLevel2();
    }

    @Override
    public List<Block> blocks() {
        List<Block> b = new ArrayList<>();
        double x = 20;
        for (int i = 0; i < 15; i++) {
            Rectangle rec = new Rectangle(new Point(x, 250), (760 / 15), 20);
            Block block1 = new Block(rec, getColor(i));
            b.add(block1);
            x += 760 / 15;
        }
        return b;
    }

    /**
     * Gets color.
     *
     * @param c the c
     * @return the color
     */
    public Color getColor(int c) {
        if (c < 2) {
            return Color.cyan;
        } else if (c < 4) {
            return Color.pink;
        } else if (c < 6) {
            return Color.blue;
        } else if (c < 9) {
            return Color.GREEN;
        } else if (c < 11) {
            return Color.yellow;
        } else if (c < 13) {
            return Color.ORANGE;
        } else {
            return Color.red;
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
