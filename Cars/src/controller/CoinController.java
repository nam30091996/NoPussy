package controller;

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
public class CoinController extends SingleController implements Colliable{
    public static final int INCREASE_SPEED_TIME = 20;
    public static final int REDUCE_SPEED_TIME = 30;
    private CoinType coinType;

    public CoinController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.gameObject.isAlive()) {
            super.paint(g);
        }
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public void run() {
        super.run();
        if(!GameConfig.getInst().isInScreen(this.gameObject.getRect())) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof CarPlayerController) {
            if(coinType == CoinType.ULTRA) {
                ((CarPlayer)c.getGameObject()).increasePoint(10);
            } else ((CarPlayer)c.getGameObject()).increasePoint();
            if(CarPlayer.getPoint() % INCREASE_SPEED_TIME == 0 && CarPlayer.getPoint() != 0) {
                EnemyCarController.increaseSpeed();
            }
        }
    }

    public static CoinController create(CoinType coinType, int x, int y) {
        Coin coin = new Coin(x, y, Coin.COIN_WIDTH, Coin.COIN_HEIGHT);
        ImageDrawer imageDrawer = null;
        CoinController coinController = null;
        switch (coinType) {
            case GREEN:
                imageDrawer = new ImageDrawer("resources/coin.png");
                coinController = new CoinController(coin, imageDrawer);
                coinController.coinType = CoinType.GREEN;
                break;
            case RED:
                imageDrawer = new ImageDrawer("resources/red_coin.png");
                coinController = new CoinController(coin, imageDrawer);
                coinController.coinType = CoinType.RED;
                break;
            case ULTRA:
                String[] images = new String[] {"resources/red_coin.png","resources/coin.png","resources/blue_coin.png"};
                AnimationDrawer animationDrawer = new AnimationDrawer(images);
                coinController = new CoinController(coin,animationDrawer);
                coinController.coinType = CoinType.ULTRA;
                break;
        }
        return coinController;
    }
}
