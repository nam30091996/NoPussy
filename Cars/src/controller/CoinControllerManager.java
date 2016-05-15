package controller;

import model.CarPlayer;
import model.Coin;
import model.GameConfig;
import view.ImageDrawer;

/**
 * Created by 1918 on 13-May-16.
 */
public class CoinControllerManager extends ControllerManager {
    private int count = 0;
    public void run() {
        super.run();
        count++;
        if (this.singleControllerVector.size() == 0 ||
                (((CoinController)this.singleControllerVector.get(0)).getCoinType() == CoinType.RED) &&
                        this.singleControllerVector.size() == 1) {
            int x = (int) (Math.random() * 300);
            int y = (int) (Math.random() * 500);
            CoinController coinController = CoinController.create(CoinType.GREEN, x, y);
            this.singleControllerVector.add(coinController);
        }
        if(CarPlayer.getPoint() % CoinController.REDUCE_SPEED_TIME == 0 && CarPlayer.getPoint() != 0 && this.singleControllerVector.size() == 1) {
            int x = (int) (Math.random() * 300);
            int y = (int) (Math.random() * 500);
            CoinController coinController = CoinController.create(CoinType.RED, x, y);
            this.singleControllerVector.add(coinController);
        }
//        if(GameConfig.getInst().durationInSeconds(count) >= 10) {
//            count = 0;
//            int x = (int) (Math.random() * 300);
//            int y = (int) (Math.random() * 500);
//            CoinController coinController = CoinController.create(CoinType.ULTRA, x, y);
//            this.singleControllerVector.add(coinController);
//        }
    }

    private static CoinControllerManager inst;
    public static CoinControllerManager getInst() {
        if(inst == null) {
            inst = new CoinControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
