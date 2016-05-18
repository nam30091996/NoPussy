package gamescenes;

import model.GameConfig;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by qhuydtvt on 5/13/2016.
 */
public class MenuGameScene extends GameScene {

    Image backgoundImage;

    public MenuGameScene() {
        this.backgoundImage = GameUtils.loadImage("resources/background_menu.png");
    }

    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.backgoundImage, 0, 0, GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(), null);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            changeGameScene(GameSceneType.PLAY);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }
}
