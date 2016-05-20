package controller.PersonController;

import controller.ControllerManager;
import gamescenes.PlayGameScene;
import model.GameConfig;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class PersonControllerManager extends ControllerManager {
    private int count = 0;

    @Override
    public void run() {
        if(!PlayGameScene.pause) {
            super.run();
            count ++;
        }
        if(GameConfig.getInst().durationInSeconds(count) >= 5) {
            count = 0;
            this.singleControllerVector.add(PersonController.create());
        }
    }

    private static PersonControllerManager inst;
    public static PersonControllerManager getInst() {
        if(inst == null) {
            inst = new PersonControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
