package graviwave.GameAction.ActionComponents;

import java.util.ArrayList;
import java.util.Random;

public class PathLists {

    private ArrayList<String> targetPath;
    private ArrayList<Function> functions;

    public PathLists(){
        this.targetPath = new ArrayList<>();
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\blue.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\green.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\orange.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\pink.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\purple.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\yellow.png");
        targetPath.add("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\target\\red.png");
        this.functions = new ArrayList<>();
        functions.add(new Function(0.5, 1, 3));
        functions.add(new Function(2, 1, 3));
        functions.add(new Function(2, 3, 1));
        functions.add(new Function(1, 3, 1));

    }
    public Function getFunction(){
        Random randX = new Random();
        int f = randX.nextInt(functions.size());
        return functions.get(f);
    }
    public String getPath() {
        Random randX = new Random();
        int f = randX.nextInt(targetPath.size());
        return targetPath.get(f);
    }
}

