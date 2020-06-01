package graviwave.GameAction.ActionComponents;

import javafx.animation.AnimationTimer;
import graviwave.GameObjects.Target;

public class Trajectory {

    public Trajectory(Target target, Function function, int number){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                target.getImageView().translateXProperty().set(target.getImageView().getTranslateX() - (function.getL() + number) * 0.5);
                target.getImageView().translateYProperty().set(target.getImageView().getTranslateX() * function.getK() + function.getP() + number);
            }
        };
        timer.start();
    }

}
