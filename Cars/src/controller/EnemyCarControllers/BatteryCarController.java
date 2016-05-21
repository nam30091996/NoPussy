package controller.EnemyCarControllers;

import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import model.EnemyCar;
import view.GameDrawer;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class BatteryCarController extends EnemyCarController implements Colliable{
    public BatteryCarController(EnemyCar gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    public BatteryCarController(EnemyCar gameObject, GameDrawer gameDrawer, EnemyCarType enemyCarType) {
        super(gameObject, gameDrawer, enemyCarType);
    }

    public BatteryCarController(EnemyCar gameObject, GameDrawer gameDrawer, EnemyCarType enemyCarType, LanePosition lanePosition) {
        super(gameObject, gameDrawer, enemyCarType, lanePosition);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof CarPlayerController) {

        }
    }
}
