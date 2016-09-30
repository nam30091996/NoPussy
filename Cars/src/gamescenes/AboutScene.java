package gamescenes;

import model.GameConfig;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class AboutScene extends GameScene {
    private final int DEFAULT_X = 10;
    private final int DEFAULT_Y = 30;
    private final int WIDTH_BUTTON = 30;
    private final int HEIGHT_BUTTON = 30;

    private Image backgroundImage;
    private Image backImage;

    public AboutScene() {
        this.backgroundImage = GameUtils.loadImage("resources/background_about.png");
        this.backImage = GameUtils.loadImage("resources/back.png");
    }

    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 0, GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(), null);
        g.drawImage(this.backImage, DEFAULT_X, DEFAULT_Y, WIDTH_BUTTON, HEIGHT_BUTTON, null);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {

    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }

    @Override
    public void onMouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x >= DEFAULT_X && x - DEFAULT_X <= WIDTH_BUTTON && y >= DEFAULT_Y && y - DEFAULT_Y <= HEIGHT_BUTTON) {
            changeGameScene(GameSceneType.MENU);
        }
    }
}
