package controller.CoinControllers;

import controller.ControllerManager;
import controller.EnemyCarControllers.EnemyCarController;
import gamescenes.Level3GameScene;
import model.CarPlayer;
import model.Coin;
import model.GameConfig;

/**
 * Created by 1918 on 13-May-16.
 */
public class CoinControllerManager extends ControllerManager {
    private int count = 0;
    public static final int INCREASE_SPEED_TIME = 10;
    public static final int REDUCE_SPEED_TIME = 20;

    public void run() {
        super.run();
        if (!Level3GameScene.pause) count++;
        if (this.singleControllerVector.size() == 0 ||
                (((Coin) this.singleControllerVector.get(0).getGameObject()).getCoinType() == CoinType.RED) &&
                        this.singleControllerVector.size() == 1) {
            int x = (int) (Math.random() * (GameConfig.DEFAULT_SCREEN_WIDTH - Coin.COIN_WIDTH));
            int y = (int) (Math.random() * (GameConfig.DEFAULT_SCREEN_HEIGHT - Coin.COIN_HEIGHT - 20));
            CoinController coinController = CoinController.create(CoinType.GREEN, x, y);
            this.singleControllerVector.add(coinController);
        }
        if (GameConfig.getInst().durationInSeconds(count) == REDUCE_SPEED_TIME && EnemyCarController.getSpeed() > 2 && CarPlayer.getPoint() != 0 && this.singleControllerVector.size() == 1) {
            this.count = 0;
            int x = (int) (Math.random() * (GameConfig.DEFAULT_SCREEN_WIDTH - Coin.COIN_WIDTH));
            int y = (int) (Math.random() * (GameConfig.DEFAULT_SCREEN_HEIGHT - Coin.COIN_HEIGHT - 20));
            CoinController coinController = CoinController.create(CoinType.RED, x, y);
            this.singleControllerVector.add(coinController);
        }
    }

    private static CoinControllerManager inst;

    public static CoinControllerManager getInst() {
        if (inst == null) {
            inst = new CoinControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
