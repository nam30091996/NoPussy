package controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by MyComputer on 5/12/2016.
 */
public class EnemyCarControllerManager extends ControllerManager {

    private static EnemyCarControllerManager inst;
    public static EnemyCarControllerManager getInst() {
        if(inst == null) {
            inst = new EnemyCarControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }

    @Override
    public void run() {
        super.run();

        //DuTQ: tạo 3 ô tô địch ở 3 lane random, thừa một lane để tránh
        //Nam: so sánh size() với 0 chứ trong trường hợp mình đâm chết 1 cái xong lập tức nó ra 3 cái mới luôn
        if (this.singleControllerVector.size() == 0) {
            List<LanePosition> shuffledLanes = Arrays.asList(LanePosition.values());
            Collections.shuffle(shuffledLanes);
            singleControllerVector.add(
                    EnemyCarController.create(EnemyCarType.BLUE,shuffledLanes.get(0))
            );
            singleControllerVector.add(
                    EnemyCarController.create(EnemyCarType.ORANGE,shuffledLanes.get(1))
            );
            singleControllerVector.add(
                    EnemyCarController.create(EnemyCarType.RED,shuffledLanes.get(2))
            );
        }
    }
}
