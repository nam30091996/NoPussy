package gamescenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by qhuydtvt on 5/13/2016.
 */


public abstract class GameScene {

    private GameSceneListener gameSceneListener;

    public void setGameSceneListener(GameSceneListener gameSceneListener) {
        this.gameSceneListener = gameSceneListener;
    }

    protected void changeGameScene(GameSceneType gameSceneType) {
        if(gameSceneListener != null) {
            gameSceneListener.changeGameScence(gameSceneType);
            gameSceneListener  = null;
        }
    }

    public abstract void run();

    public abstract void paint(Graphics g);

    public abstract void onKeyPressed(KeyEvent e);

    public abstract void onKeyReleased(KeyEvent e);
}
