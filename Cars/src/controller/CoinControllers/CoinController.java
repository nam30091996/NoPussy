package controller.CoinControllers;

import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.EnemyCarControllers.EnemyCarController;
import controller.SingleController;
import model.CarPlayer;
import model.Coin;
import model.GameConfig;
import model.GameObject;
import view.AnimationDrawer;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by 1918 on 13-May-16.
 */
public class CoinController extends SingleController implements Colliable {

    public CoinController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (this.gameObject.isAlive()) {
            super.paint(g);
        }
    }

    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject.getRect())) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof CarPlayerController) {
            this.gameObject.setAlive(false);
            ((CarPlayer) c.getGameObject()).increasePoint();
            if (CarPlayer.getPoint() % CoinControllerManager.INCREASE_SPEED_TIME == 0 && CarPlayer.getPoint() != 0) {
                EnemyCarController.increaseSpeed();
            }
        }
    }

    public static CoinController create(CoinType coinType, int x, int y) {
        Coin coin = new Coin(x, y, Coin.COIN_WIDTH, Coin.COIN_HEIGHT, coinType);
        ImageDrawer imageDrawer = null;
        CoinController coinController = null;
        switch (coinType) {
            case GREEN:
                imageDrawer = new ImageDrawer("resources/coin.png");
                coinController = new CoinController(coin, imageDrawer);
                break;
            case RED:
                imageDrawer = new ImageDrawer("resources/red_coin.png");
                coinController = new CoinController(coin, imageDrawer);
                break;
        }
        return coinController;
    }
}
