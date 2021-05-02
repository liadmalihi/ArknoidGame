//ID:318948809

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameFlow;
import interfaces.LevelInformation;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 5 game.
 */
public class Ass6Game {

    /**
     * The entry point of application.
     * The function accepts arguments, any number from 1 to 4 is added to the list
     * and runs the steps according to the list.
     * If the list is empty from step 1 to 4 in order
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI g = new GUI("game", 800, 600);
        AnimationRunner an = new AnimationRunner(g);
        KeyboardSensor keyboard = g.getKeyboardSensor();
        GameFlow game = new GameFlow(an, keyboard);
        List<LevelInformation> level = new ArrayList<>();
        String[] arg={"1","2","3","4"};
        for (int i = 0; i < arg.length; i++) {
            String s = arg[i];
            if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                int n = Integer.parseInt(arg[i]);
                if (n == 1) {
                    level.add(new Level1());
                }
                if (n == 2) {
                    level.add(new Level2());
                }
                if (n == 3) {
                    level.add(new Level3());
                }
                if (n == 4) {
                    level.add(new Level4());
                }
            }
        }
        if (level.size() == 0) {
            level.add(new Level1());
            level.add(new Level2());
            level.add(new Level3());
            level.add(new Level4());
        }
        game.runLevels(level);


    }
}
