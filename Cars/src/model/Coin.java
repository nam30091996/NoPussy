package model;

import controller.CoinControllers.CoinType;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class Coin extends GameObject {
    public static final int COIN_WIDTH = 30;
    public static final int COIN_HEIGHT = 30;
    private CoinType coinType;

    public Coin(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Coin(int x, int y, int width, int height, CoinType coinType) {
        super(x, y, width, height);
        this.coinType = coinType;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }
}
