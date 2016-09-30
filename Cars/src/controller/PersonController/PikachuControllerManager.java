package controller.PersonController;

import controller.ControllerManager;
import gamescenes.Level3GameScene;
import model.GameConfig;

/**
 * Created by 1918 on 21-May-16.
 */
public class PikachuControllerManager extends ControllerManager {
    private int count = 0;

    @Override
    public void run() {
        if (!Level3GameScene.pause) {
            super.run();
            count++;
        }
        if (GameConfig.getInst().durationInSeconds(count) >= 10) {
            count = 0;
            this.singleControllerVector.add(PikachuController.create());
        }
    }

    private static PikachuControllerManager inst;

    public static PikachuControllerManager getInst() {
        if (inst == null) {
            inst = new PikachuControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
