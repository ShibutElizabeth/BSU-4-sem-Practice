package graviwave.GameAction;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import graviwave.GameAction.ActionComponents.PathLists;
import graviwave.GameObjects.Target;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class TargetPanel {
    private Pane pane;
    private Game game;
    private PathLists pathLists;
    private ArrayList<Target> targets;
    TargetPanel(Game game){
        this.pane = new Pane();
        this.game = game;
        this.pathLists = new PathLists();
        this.targets = new ArrayList<>();
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timer.start();
                t.cancel();
            }
        };
        t.schedule(timerTask, 4000);
    }

    Pane getPane() { return pane; }

    private AnimationTimerExt timer = new AnimationTimerExt(2000) {
        @Override
        public void handle() {
            setTarget();
        }
    };

    private void setTarget(){
        Target target = new Target(game.getLevel().number, pathLists.getPath(), pathLists.getFunction());
        targets.add(target);
        pane.getChildren().add(target.getImageView());
    }

    ArrayList<Target> getTargets() {
        return targets;
    }

    AnimationTimerExt getTimer() {
        return timer;
    }
}


abstract class AnimationTimerExt extends AnimationTimer {

    private long sleepNs;

    private long prevTime = 0;

    AnimationTimerExt(long sleepMs) {
        this.sleepNs = sleepMs * 1_000_000;
    }

    @Override
    public void handle(long now) {
        // some delay
        if ((now - prevTime) < sleepNs) {
            return;
        }
        prevTime = now;
        handle();
    }

    public abstract void handle();

}
