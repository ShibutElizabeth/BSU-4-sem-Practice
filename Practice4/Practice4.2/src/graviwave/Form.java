package graviwave;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

class Form extends JFrame {
    Form(){
        super("Practice 4.2");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ignored) {
        }
        JPanel mPanel = new JPanel();
        mPanel.setBackground(Color.BLACK);
        mPanel.add(new PointOnCircle());
        add(mPanel);
        pack();
        setSize(800, 800);
        setVisible(true);
    }
}

class PointOnCircle extends JPanel {
    private int m = 1;
    private int val;
    private double angle;
    private Image image;
    private JRadioButton moon = new JRadioButton("Moon", true);
    private JRadioButton earth = new JRadioButton("Earth");
    private JRadioButton jupiter = new JRadioButton("Jupiter");
    private JRadioButton mercury = new JRadioButton("Mercury");
    private JRadioButton mars = new JRadioButton("Mars");
    private JRadioButton venus = new JRadioButton("Venus");
    private JRadioButton sun = new JRadioButton("Sun");
    private JRadioButton saturn = new JRadioButton("Saturn");
    private JButton left = new JButton("Left");
    private JButton right = new JButton("Right");
    private String imPath = "Moon";

    PointOnCircle() {
        setPreferredSize(new Dimension(800, 800));
        JPanel setPanel = new JPanel();
        setPanel.setPreferredSize(new Dimension(700, 100));
        setPanel.setLayout(new GridLayout(3, 4));
        ButtonGroup planets = new ButtonGroup();
        planets.add(moon);
        planets.add(earth);
        planets.add(jupiter);
        planets.add(mercury);
        planets.add(sun);
        planets.add(saturn);
        planets.add(mars);
        planets.add(venus);

        setPanel.add(mercury);
        setPanel.add(venus);
        setPanel.add(moon);
        setPanel.add(earth);
        setPanel.add(mars);
        setPanel.add(jupiter);
        setPanel.add(saturn);
        setPanel.add(sun);

        JSlider slider = new JSlider(0, 10, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        setPanel.add(slider);
        left.setPreferredSize(new Dimension(50, 20));
        right.setPreferredSize(new Dimension(50, 20));
        setPanel.add(left);
        setPanel.add(right);
        left.addActionListener(this::actionPerformed);
        right.addActionListener(this::actionPerformed);

        add(setPanel);
        moon.addActionListener(this::actionPerformed);
        earth.addActionListener(this::actionPerformed);
        jupiter.addActionListener(this::actionPerformed);
        mercury.addActionListener(this::actionPerformed);
        mars.addActionListener(this::actionPerformed);
        sun.addActionListener(this::actionPerformed);
        saturn.addActionListener(this::actionPerformed);
        venus.addActionListener(this::actionPerformed);
        angle = 0;
        Timer t1 = new Timer();
        val = 1;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                angle += m * 0.05 * val;
                repaint();
            }
        };
        t1.schedule(timerTask, 10, 100);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if(!source.getValueIsAdjusting()){
                val = source.getValue();
            }
        });
    }
    private void loadImage() {
        try {
            image = ImageIO.read(new File(
                    "C:\\Users\\Лиза\\IdeaProjects\\Practice4.2\\src\\plain\\" + imPath +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        loadImage();
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        Graphics2D g2d = (Graphics2D) g;
        int x =  width / 2 - 100;
        int y = height / 2 -100;
        double r = 0.65 * Math.min(x, y);
        x += r * Math.cos(angle);
        y += r * Math.sin(angle);
        g2d.drawImage(image,x, y, null);
    }
    private void actionPerformed(ActionEvent e) {
        if (e.getSource() == left){
            m = -1;
        }
        else if(e.getSource() == right){
            m = 1;
        }
        if (e.getSource() == moon) {
            imPath = "Moon";
        }
        else if (e.getSource() == jupiter){
            imPath = "Jupiter";
        }
        else if (e.getSource() == earth){
            imPath = "Earth";
        }
        else if (e.getSource() == mercury){
            imPath = "Mercury";
        }
        else if (e.getSource() == venus){
            imPath = "Venus";
        }
        else if (e.getSource() == mars){
            imPath = "Mars";
        }
        else if (e.getSource() == saturn){
            imPath = "Saturn";
        }
        else if (e.getSource() == sun){
            imPath = "Sun";
        }
    }
}
