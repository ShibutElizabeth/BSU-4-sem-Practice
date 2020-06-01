package graviwave.GameAction;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import graviwave.GameDesign.Base;
import graviwave.GameDesign.DesignComponents.IconPng;
import graviwave.GameDesign.MusicalArrangement;

import java.util.ArrayList;

public class SettingsIcons {
    private ArrayList<IconPng> icons;
    private IconPng play;
    private IconPng soundOn;
    private IconPng soundOff;
    private IconPng sound;
    private IconPng restart;

    private MusicalArrangement musicalArrangement;
    private Game game;
    private Group group;
    private int f = 1;
    public SettingsIcons(Base base, MusicalArrangement musicalArrangement) throws Exception{
        icons = new ArrayList<>();
        this.play = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\playLittle.png");
        this.soundOn = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\soundOnLittle.png");
        this.soundOff = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\soundOffLittle.png");
        this.sound = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\soundOnLittle.png");
        this.restart = new IconPng("C:\\Users\\Лиза\\IdeaProjects\\Practice_9\\src\\Components\\settings\\restartLittle.png");

        this.musicalArrangement = musicalArrangement;
        icons.add(soundOn);
        icons.add(soundOff);
        this.group = new Group();
        Point2D pointPlay = new Point2D(400, 250);
        group.getChildren().add(play.getImageView());
        play.getImageView().setX(pointPlay.getX());
        play.getImageView().setY(pointPlay.getY());
        setActionPlay(base);
        //setActionSound();
    }

    void setActionRestart(Game game){
        restart.getImageView().setOnMouseClicked(event -> {
            musicalArrangement.getClick().getPlayer().play();
            group.getChildren().clear();
            Level level = game.getLevel();
            int l = game.getGameLevel().getNumberOfLevel();
            if(game.timePanel.getImageView().isVisible()){
                game.timePanel.getImageView().setVisible(false);

                game.getScore().setCount(0);
                game.getScore().scoreUpdate(0);
                level.levelUpdate(0);
            }
            else{
                game.getScore().setCount(l*10);
                game.getScore().scoreUpdate(0);
                level.levelUpdate(l);
            }

        });

    }

    private void setActionPlay(Base base){
        play.getImageView().setOnMouseClicked(event -> {
            musicalArrangement.getClick().getPlayer().play();
            musicalArrangement.playPlayerPlay();
            play.getImageView().setVisible(false);
            group.getChildren().clear();
            this.game = new Game(base);
        });

    }
    public void setActionSound(){
        sound.getImageView().setOnMouseClicked(event -> {
            musicalArrangement.getClick().getPlayer().play();
            sound.getImageView().setImage(icons.get(f).getImageView().getImage());
            if(f == 0){
                f = 1;
                if(play.getImageView().isVisible()){
                    musicalArrangement.getBackgroundMenu().getPlayer().play();
                }
                else{
                    musicalArrangement.getGame().getPlayer().play();
                }
            }
            else{
                f = 0;
                if(play.getImageView().isVisible()){
                    musicalArrangement.getBackgroundMenu().getPlayer().pause();
                }
                else{
                    musicalArrangement.getGame().getPlayer().pause();
                }
            }


        });
    }
    void setGameActionSound(){
        sound.getImageView().setOnMouseClicked(event -> {
            musicalArrangement.getClick().getPlayer().play();
            sound.getImageView().setImage(icons.get(f).getImageView().getImage());
            if(f == 0){
                f = 1;
                musicalArrangement.getGame().getPlayer().setMute(false);
            }
            else{
                f = 0;
                musicalArrangement.getGame().getPlayer().setMute(true);
            }

        });

    }

    public IconPng getRestart() {
        return restart;
    }

    public Group getGroup() {
        return group;
    }

    public IconPng getSound() {
        return sound;
    }

    public Game getGame() {
        return game;
    }
}
