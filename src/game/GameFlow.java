package game;

import background.LoseScreen;
import background.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import hitlistener.Counter;
import interfaces.LevelInformation;

import java.util.List;


/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor kbs;
    private Counter score;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        kbs = ks;
        score = new Counter();
        gui = ar.getGui();
    }

    /**
     * Run levels.
     * The function goes through the list and runs the steps in order.
     * If disqualified or won displays a message accordingly and the game ends
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int win = 0;
        for (LevelInformation levelInfo : levels) {
            win = 0;
            GameLevel level = new GameLevel(levelInfo, this.kbs, this.animationRunner, score);
            level.initialize();
            while (level.getBlocksCount() != 0 && level.getBallsCount() != 0) {
                level.run();
            }
            if (level.getBlocksCount() == 0) {
                win = 1;
                score.increase(100);
            }
            if (level.getBallsCount() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(kbs, KeyboardSensor.SPACE_KEY,
                        new LoseScreen(kbs, score)));
                gui.close();
            }
        }
        if (win == 1) {
            this.animationRunner.run(new KeyPressStoppableAnimation(kbs, KeyboardSensor.SPACE_KEY,
                    new WinScreen(kbs, score)));
            gui.close();

        }

    }
}
