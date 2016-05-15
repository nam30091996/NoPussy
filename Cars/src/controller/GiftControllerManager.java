package controller;

import model.GameConfig;

/**
 * Created by 1918 on 15-May-16.
 */
public class GiftControllerManager extends ControllerManager {
    public static final int TIME = 25;
    private int count = 0;
    private static GiftControllerManager inst;
    public static GiftControllerManager getInst() {
        if(inst == null) {
            inst = new GiftControllerManager();
        }
        return inst;
    }

    @Override
    public void run() {
        super.run();
        count++;
        if(GameConfig.getInst().durationInSeconds(count) >= TIME && this.singleControllerVector.size() == 0) {
            this.count = 0;
            int x = (int)(Math.random() * 3) ;
            if(x % 3 == 0) {
                this.singleControllerVector.add(GiftController.create(GiftType.SHOOT,(int)(Math.random()*GameConfig.DEFAULT_SCREEN_WIDTH),0));
            } else if(x % 3 == 1) {
                this.singleControllerVector.add(GiftController.create(GiftType.SHIELD,(int)(Math.random()*GameConfig.DEFAULT_SCREEN_WIDTH),0));
            } else if(x % 3 == 2) {
                this.singleControllerVector.add(GiftController.create(GiftType.HEART,(int)(Math.random()*GameConfig.DEFAULT_SCREEN_WIDTH),0));
            }
        }
    }

    public static void setNull() {
        inst = null;
    }
}
