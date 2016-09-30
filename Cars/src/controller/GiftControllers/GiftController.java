package controller.GiftControllers;

import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.SingleController;
import model.GameConfig;
import model.Gift;
import view.GameDrawer;
import view.ImageDrawer;

/**
 * Created by 1918 on 15-May-16.
 */
public class GiftController extends SingleController implements Colliable {

    private static int SPEED = 7;

    public GiftController(Gift gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = SPEED;
        CollisionPool.getInst().add(this);
    }

    public void onCollide(Colliable c) {
        if (c instanceof CarPlayerController) {
            this.gameObject.setAlive(false);
        }
    }

    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject) && !GameConfig.getInst().isInStartPosition(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    public static GiftController create(GiftType giftType, int x, int y) {
        Gift gift = new Gift(x, y, Gift.WIDTH, Gift.HEIGHT, giftType);
        ImageDrawer imageDrawer = null;
        switch (giftType) {
            case SHOOT:
                imageDrawer = new ImageDrawer("resources/gift.png");
                break;
            case SHIELD:
                imageDrawer = new ImageDrawer("resources/shield.png");
                break;
            case HEART:
                imageDrawer = new ImageDrawer("resources/heart.png");
                break;
            case FLY:
                imageDrawer = new ImageDrawer("resources/wing.png");
                break;
        }
        GiftController giftController = new GiftController(gift, imageDrawer);
        return giftController;
    }
}
