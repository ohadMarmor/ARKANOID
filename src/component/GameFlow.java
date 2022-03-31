package component;

import biuoop.GUI;
import geometry.Counter;
import interfaces.Animation;
import java.util.List;

/**
 * @author Ohad Marmor - 207481524.
 */
public class GameFlow {

    // field:
    private Counter counter = new Counter(0);
    private biuoop.GUI gui = new GUI("Arkanoid", 800, 600);

    /**
     * constructor.
     */
    public GameFlow() {
    }

    /**
     * this method receives a list of levels and run them one by one, until winning or loosing.
     * @param levels - the list with the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        biuoop.KeyboardSensor k = this.gui.getKeyboardSensor();
        biuoop.DrawSurface d = this.gui.getDrawSurface();
        AnimationRunner runner = new AnimationRunner(this.gui, 20);
        // runs the levels:
        for (LevelInformation levelInfo : levels) {
            GameEnvironment e = new GameEnvironment();
            GameLevel level = new GameLevel(levelInfo, levelInfo.getEnvironment(), this.counter, this.gui);
            level.initialize();
            // run each level until loosing or passing:
            while (level.getBlocksNumber().getValue() != 0 && level.getBallsNumber().getValue() != 0) {
                level.run();
            }
            this.counter.increase(100);
            // case loosing:
            if (level.getBallsNumber().getValue() == 0) {
                Animation endScreen = new EndScreen(0, this.counter.getValue(), k);
                runner.run(endScreen);
                this.gui.close();
            }
        }
        Animation endScreen = new EndScreen(1, this.counter.getValue(), k);
        runner.run(endScreen);
        this.gui.close();
    }
}