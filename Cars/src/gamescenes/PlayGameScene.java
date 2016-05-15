package gamescenes;

import controller.*;
import model.CarPlayer;
import model.GameConfig;
import util.GameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by qhuydtvt on 5/13/2016.
 */
public class PlayGameScene extends GameScene {

    private CarPlayerController carPlayerController;
    private Image backgroundImage;
    private GameConfig gameConfig;
    private Vector<Controller> controllerVect;
    private int count = 0;

    public PlayGameScene() {


        this.gameConfig = GameConfig.getInst();

        controllerVect = new Vector<Controller>();
        controllerVect.add(CarPlayerController.getCarPlayerController());
        controllerVect.add(CoinControllerManager.getInst());
        controllerVect.add(StoneControllerManager.getInst());
        controllerVect.add(EnemyCarControllerManager.getInst());
        controllerVect.add(GamePointControllerManager.getInst());
        controllerVect.add(CarPlayerHPControllerManager.getInst());
        controllerVect.add(GiftControllerManager.getInst());



        this.carPlayerController = CarPlayerController.getCarPlayerController();

        try {
            this.backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        CollisionPool.getInst().run();
        for(Controller controller : controllerVect) {
            controller.run();
        }

        GamePointControllerManager.getInst().updatePoint(((CarPlayer) carPlayerController.getGameObject()).getPoint());
        CarPlayerHPControllerManager.getInst().updateHP(((CarPlayer) carPlayerController.getGameObject()).getHp());
        if(!carPlayerController.getGameObject().isAlive()) {
            reset();
            changeGameScene(GameSceneType.GAMEOVER);
        }

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        for (Controller controller : controllerVect) {
            controller.paint(g);
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        CarPlayerDirection carPlayerDirection = CarPlayerDirection.NONE;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                carPlayerDirection = CarPlayerDirection.UP;
                break;
            case KeyEvent.VK_DOWN:
                carPlayerDirection = CarPlayerDirection.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                carPlayerDirection = CarPlayerDirection.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                carPlayerDirection = CarPlayerDirection.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                carPlayerController.shoot();
                break;
            case KeyEvent.VK_P:
                for (Controller controller : controllerVect) {
                    if(controller instanceof SingleController) {
                        ((SingleController) controller).setPause(true);
                    }
                    else {
                        ((ControllerManager) controller).setPause(true);
                    }
                }
                break;
            case KeyEvent.VK_R:
                for (Controller controller : controllerVect) {
                    if(controller instanceof SingleController) {
                        ((SingleController) controller).setPause(false);
                    }
                    else {
                        ((ControllerManager) controller).setPause(false);
                    }
                }
                break;
        }

        carPlayerController.move(carPlayerDirection);

    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        CarPlayerDirection carPlayerDirection = CarPlayerDirection.NONE;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                carPlayerDirection = CarPlayerDirection.STOP_Y;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                carPlayerDirection = CarPlayerDirection.STOP_X;
                break;
        }
        carPlayerController.move(carPlayerDirection);
    }

    public void reset() {
        CoinControllerManager.setNull();
        EnemyCarControllerManager.setNull();
        StoneControllerManager.setNull();
        CarPlayerController.setNull();
        GiftControllerManager.setNull();
        EnemyCarController.setSpeed(EnemyCarController.SPEED);
        CollisionPool.getInst().reset();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }
}
