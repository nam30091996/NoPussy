import gamescenes.*;
import model.GameConfig;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class GameWindow extends Frame implements Runnable,GameSceneListener {

    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    GameConfig gameConfig;
    GameScene gameScene;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    GameWindow() {
        this.gameConfig = GameConfig.getInst();
        gameScene = new MenuGameScene();
        gameScene.setGameSceneListener(this);
        this.setTitle("NoPussy");
        GameUtils.playSound("resources/background_sound.wav", true);


        this.setVisible(true);
        this.setLocation((int)(width - GameConfig.DEFAULT_SCREEN_WIDTH) / 2, (int)(height - GameConfig.DEFAULT_SCREEN_HEIGHT) / 2);
        this.setSize(GameConfig.DEFAULT_SCREEN_WIDTH, GameConfig.DEFAULT_SCREEN_HEIGHT);


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                gameScene.onKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameScene.onKeyReleased(e);
            }
        });

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();

        gameScene.paint(backbufferGraphics);

        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                gameScene.run();
                repaint();
                Thread.sleep(gameConfig.getThreadDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeGameScence(GameSceneType gameSceneType) {
        switch (gameSceneType) {
            case PLAY:
                gameScene = new PlayGameScene();
                gameScene.setGameSceneListener(this);
                break;
            case MENU:
                gameScene = new MenuGameScene();
                gameScene.setGameSceneListener(this);
                break;
            case GAMEOVER:
                gameScene = new GameOverScene();
                gameScene.setGameSceneListener(this);
                break;
        }
    }
}
