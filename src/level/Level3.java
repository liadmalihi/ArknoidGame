package level;

import background.BLevel3;
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
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            v.add(Velocity.fromAngleAndSpeed(45, 8));
        }
        for (int i = numberOfBalls() / 2; i < numberOfBalls(); i++) {
            v.add(Velocity.fromAngleAndSpeed(315, 8));
        }
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        String s = "Green 3";
        return s;
    }

    @Override
    public Sprite getBackground() {
        return new BLevel3();
    }

    @Override
    public List<Block> blocks() {
        List<Block> b = new ArrayList<>();
        Point p = new Point(730, 150);
        for (int i = 0; i < 5; i++) {
            //loop-block in line
            for (int r = 0; r < 10 - i; r++) {
                Rectangle rec = new Rectangle(p, 40, 20);
                Block block1 = new Block(rec, getColor(i));
                p = new Point(p.getX() - 40, p.getY());
                b.add(block1);
            }
            p = new Point(730, p.getY() + 20);
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
        switch (c) {
            case 0:
                return Color.LIGHT_GRAY;
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.white;
            default:
                return null;
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
