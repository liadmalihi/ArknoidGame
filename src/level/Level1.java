package level;

import background.BLevel1;
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
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            v.add(Velocity.fromAngleAndSpeed(360, 10));
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
        String s = "Direct Hit";
        return s;
    }

    @Override
    public Sprite getBackground() {
        return new BLevel1();
    }

    @Override
    public List<Block> blocks() {
        List<Block> b = new ArrayList<>();
        Rectangle rec = new Rectangle(new Point(390, 200), 20, 20);
        Block block1 = new Block(rec, Color.RED);
        b.add(block1);
        return b;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
