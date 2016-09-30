package controller.EnemyCarControllers;

import controller.ControllerManager;
import gamescenes.Level3GameScene;
import model.GameConfig;

/**
 * Created by MyComputer on 5/12/2016.
 */
public class EnemyCarControllerManager extends ControllerManager {

    private int time = 0;

    private static EnemyCarControllerManager inst;

    public static EnemyCarControllerManager getInst() {
        if (inst == null) {
            inst = new EnemyCarControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }

    @Override
    public void run() {
        if (!Level3GameScene.pause) {
            super.run();
            time++;
        }

        //DuTQ: tạo 3 ô tô địch ở 3 lane random, thừa một lane để tránh
        //Nam: so sánh size() với 0 chứ trong trường hợp mình đâm chết 1 cái xong lập tức nó ra 3 cái mới luôn
        if (((this.singleControllerVector.size() == 0) || (this.singleControllerVector.size() == 1 && this.singleControllerVector.get(0).getGameObject().getY() >= GameConfig.DEFAULT_SCREEN_HEIGHT / 3))
//                || (this.singleControllerVector.size() < 4 && this.singleControllerVector.get(0).getGameObject().getY() >= GameConfig.DEFAULT_SCREEN_HEIGHT / 3)
                ) {

            int count = (int) (Math.random() * 3);
            EnemyCarController car1;
            EnemyCarType enemyCarType = null;
            if (GameConfig.getInst().durationInSeconds(time) >= 20) {
                time = 0;
                car1 = EnemyCarController.create(EnemyCarType.BATTERY, LanePosition.getRandom());
            } else {
                do {
                    enemyCarType = EnemyCarType.getRandom();
//                    enemyCarType = EnemyCarType.BLACK;
                } while (enemyCarType == EnemyCarType.BATTERY);
                car1 = EnemyCarController.create(enemyCarType, LanePosition.getRandom());
            }
            do {
                enemyCarType = EnemyCarType.getRandom();
//                enemyCarType = EnemyCarType.BLACK;
            } while (enemyCarType == EnemyCarType.BATTERY);
            EnemyCarController car2 = EnemyCarController.create(enemyCarType, LanePosition.getRandom());
//            EnemyCarController car3 =  EnemyCarController.create(EnemyCarType.getRandom(),LanePosition.getRandom());
            if (car2.getGameObject().getY() == car1.getGameObject().getY() && car2.getGameObject().getX() == car1.getGameObject().getX()) {
                car2.getGameObject().setY(car1.getGameObject().getY() - GameConfig.DEFAULT_SCREEN_HEIGHT / 2);
            }
//            if (car3.getGameObject().getY() == car1.getGameObject().getY() && car3.getGameObject().getX() == car1.getGameObject().getX()) {
//                car3.getGameObject().setY(car1.getGameObject().getY() - GameConfig.DEFAULT_SCREEN_HEIGHT / 2);
//            }
//            if (car3.getGameObject().getY() == car2.getGameObject().getY() && car3.getGameObject().getX() == car2.getGameObject().getX()) {
//                car3.getGameObject().setY(car2.getGameObject().getY() - GameConfig.DEFAULT_SCREEN_HEIGHT / 2);
//            }
            switch (count) {
                case 1:
                    singleControllerVector.add(
                            car1);
                    break;
                case 2:

                    singleControllerVector.add(
                            car1
                    );
                    singleControllerVector.add(
                            car2
                    );

                    break;
//                case 3:
//                    singleControllerVector.add(
//                            car1
//                    );
//                    singleControllerVector.add(
//                            car2
//                    );
//                    singleControllerVector.add(
//                            car3
//                    );
//                    break;
            }
        }
    }
}
