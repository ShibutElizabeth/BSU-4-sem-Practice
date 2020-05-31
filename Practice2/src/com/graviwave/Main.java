package com.graviwave;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        //System.out.println("******  ALL SUGGESTIONS ******");
        controller.open("C:\\Users\\Лиза\\IdeaProjects\\Practice2\\Input.xml");
        controller.menu();
        //controller.showFilter();
    }
}
