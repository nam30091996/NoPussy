package gamescenes;

import controller.PointControllers.GamePointControllerManager;
import controller.PointControllers.HighScoreManager;
import model.CarPlayer;
import model.GameConfig;
import model.HighScore;
import util.GameUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by 1918 on 15-May-16.
 */
public class GameOverScene extends GameScene {
    public static final int DEFAULT_X[] = new int[]{190, 180, 160, 145, 130};
    public static final int DEFAULT_HIGHSCORE_Y = 108;
    public static final int DEFAULT_SCORE_Y = 210;


    Image background;

    public GameOverScene() {
        if(HighScore.getHighScore() < CarPlayer.getPoint()) {
            HighScore.setHighScore(CarPlayer.getPoint());

            int point = HighScore.getHighScore();
            String cPoint = new String();
            cPoint = String.valueOf(point);

            FileOutputStream out= null;
            try {
                out = new FileOutputStream("resources/best.txt",false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            PrintWriter pw= new PrintWriter(out);
            pw.print(cPoint);
            pw.close();

        }

        this.background = GameUtils.loadImage("resources/background_game_over.png");
        GamePointControllerManager.getInst().paintPointGameOver(CarPlayer.getPoint());
        HighScoreManager.getInst().updateHighScore(HighScore.getHighScore());
    }

    @Override
    public void run() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0, GameConfig.getInst().getScreenWidth(), GameConfig.getInst().getScreenHeight(),null);
        GamePointControllerManager.getInst().paint(g);
        HighScoreManager.getInst().paint(g);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            HighScoreManager.setNull();
            GamePointControllerManager.setNull();
            CarPlayer.setPoint(0);
            changeGameScene(GameSceneType.MENU);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }

    @Override
    public void onMouseClicked(MouseEvent e) {

    }

    public void reset() {
        GamePointControllerManager.setNull();
    }
}
