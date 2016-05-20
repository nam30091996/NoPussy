package gamescenes;

import model.GameConfig;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by qhuydtvt on 5/13/2016.
 */
public class MenuGameScene extends GameScene {
    public final int DEFAULT_Y = 600;
    public final int[] DEFAULT_X = new int[]{40, 230};
    public final int WIDTH_BUTTON = 150;
    public final int HEIGHT_BUTTON = 60;

    Image backgroundImage;
    Image startImage;
    Image guideImage;

    public MenuGameScene() {
        this.backgroundImage = GameUtils.loadImage("resources/startscreen.png");
        this.startImage = GameUtils.loadImage("resources/start.png");
        this.guideImage = GameUtils.loadImage("resources/tutorial.png");
    }


    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 0, GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(), null);
        g.drawImage(this.startImage, DEFAULT_X[0], DEFAULT_Y, WIDTH_BUTTON, HEIGHT_BUTTON, null);
        g.drawImage(this.guideImage, DEFAULT_X[1], DEFAULT_Y, WIDTH_BUTTON, HEIGHT_BUTTON, null);
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
        if(x >= DEFAULT_X[0] && x - DEFAULT_X[0] <= WIDTH_BUTTON && y >= DEFAULT_Y && y - DEFAULT_Y <= HEIGHT_BUTTON) {
            changeGameScene(GameSceneType.PLAY);
        }
        else if(x >= DEFAULT_X[1] && x - DEFAULT_X[1] <= WIDTH_BUTTON && y >= DEFAULT_Y && y - DEFAULT_Y <= HEIGHT_BUTTON) {
            changeGameScene(GameSceneType.GUIDE);
        }
    }
}
