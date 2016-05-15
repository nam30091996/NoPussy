package gamescenes;

import controller.GamePointControllerManager;
import model.CarPlayer;
import model.GameConfig;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 1918 on 15-May-16.
 */
public class GameOverScene extends GameScene {
    Image background;

    public GameOverScene() {
        this.background = GameUtils.loadImage("resources/background_game_over.png");
        GamePointControllerManager.getInst().updatePointGameOver(CarPlayer.getPoint());
    }

    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0, GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(),null);
        GamePointControllerManager.getInst().paint(g);

    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GamePointControllerManager.setNull();
            CarPlayer.setPoint(0);
            changeGameScene(GameSceneType.MENU);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }

    public void reset() {
        GamePointControllerManager.setNull();
    }
}
