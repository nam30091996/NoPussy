import gamescenes.*;
import model.GameConfig;
import model.HighScore;
import util.GameUtils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class GameWindow extends Frame implements Runnable, GameSceneListener {

    private Thread thread;
    private Image backbufferImage;
    private GameConfig gameConfig;
    private GameScene gameScene;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    GameWindow() {
        this.gameConfig = GameConfig.getInst();
        gameScene = new MenuGameScene();
        gameScene.setGameSceneListener(this);
        this.setTitle("NoPussy");
        GameUtils.playSound("resources/background_sound.wav", true);

        FileInputStream in = null;
        try {
            in = new FileInputStream("resources/best.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(in);
        HighScore.setHighScore(sc.nextInt());
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
        this.setLocation((int) (width - GameConfig.DEFAULT_SCREEN_WIDTH) / 2, (int) (height - GameConfig.DEFAULT_SCREEN_HEIGHT) / 2);
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


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameScene.onMouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        if (backbufferImage == null) {
            backbufferImage = new BufferedImage(gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), 1);
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
    public void onGameSceneChanged(GameSceneType gameSceneType) {
        switch (gameSceneType) {
            case LEVEL_3:
                gameScene = new Level3GameScene();
                gameScene.setGameSceneListener(this);
                break;
            case MENU:
                gameScene = new MenuGameScene();
                gameScene.setGameSceneListener(this);
                break;
            case GAME_OVER:
                gameScene = new GameOverScene();
                gameScene.setGameSceneListener(this);
                break;
            case GUIDE:
                gameScene = new GuideScene();
                gameScene.setGameSceneListener(this);
                break;
            case ABOUT:
                gameScene = new AboutScene();
                gameScene.setGameSceneListener(this);
                break;
            case LEVEL_1:
                gameScene = new Level1GameScene();
                gameScene.setGameSceneListener(this);
                break;
            case LEVEL_2:
                gameScene = new Level2GameScene();
                gameScene.setGameSceneListener(this);
                break;
        }
    }
}
