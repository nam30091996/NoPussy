package model;

import controller.GiftControllers.GiftType;

/**
 * Created by 1918 on 15-May-16.
 */
public class Gift extends GameObject {
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    private GiftType giftType;

    public Gift(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Gift(int x, int y, int width, int height, GiftType giftType) {
        super(x, y, width, height);
        this.giftType = giftType;
    }

    public GiftType getGiftType() {
        return giftType;
    }

    public void setGiftType(GiftType giftType) {
        this.giftType = giftType;
    }
}
