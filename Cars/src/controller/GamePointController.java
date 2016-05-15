package controller;

import model.CarPlayer;
import model.GameObject;
import model.GamePoint;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by tqdu on 5/14/2016.
 */
public class GamePointController extends SingleController {

    public static final int DEFAULT_X = 10;
    public static final int DEFAULT_Y = 40;

    public GamePointController(GamePoint gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public static GamePointController create(int number, int x, int y, int width, int height) {
        GamePoint gamePoint = new GamePoint(x, y, width, height);
        GamePointController gamePointController = null;
        ImageDrawer imageDrawer = null;
        switch (number) {
            case 0:
                imageDrawer = new ImageDrawer("resources/num0.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 1:
                imageDrawer = new ImageDrawer("resources/num1.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 2:
                imageDrawer = new ImageDrawer("resources/num2.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 3:
                imageDrawer = new ImageDrawer("resources/num3.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 4:
                imageDrawer = new ImageDrawer("resources/num4.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 5:
                imageDrawer = new ImageDrawer("resources/num5.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 6:
                imageDrawer = new ImageDrawer("resources/num6.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 7:
                imageDrawer = new ImageDrawer("resources/num7.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 8:
                imageDrawer = new ImageDrawer("resources/num8.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
            case 9:
                imageDrawer = new ImageDrawer("resources/num9.png");
                gamePointController = new GamePointController(gamePoint,imageDrawer);
                break;
        }
        return gamePointController;
    }
}
