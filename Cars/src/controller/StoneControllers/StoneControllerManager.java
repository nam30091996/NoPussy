package controller.StoneControllers;

import controller.ControllerManager;
import gamescenes.Level3GameScene;
import model.GameConfig;

/**
 * Created by 1918 on 15-May-16.
 */
public class StoneControllerManager extends ControllerManager {
    public static final int TIME = 15;

    private int count = 0;
    private static StoneControllerManager inst;

    public static StoneControllerManager getInst() {
        if (inst == null) {
            inst = new StoneControllerManager();
        }
        return inst;
    }

    @Override
    public void run() {
        super.run();
        if (!Level3GameScene.pause) count++;
        if (GameConfig.getInst().durationInSeconds(count) >= TIME && this.singleControllerVector.size() == 0) {
            this.count = 0;
            this.singleControllerVector.add(StoneController.create());
        }
    }

    public static void setNull() {
        inst = null;
    }
}
