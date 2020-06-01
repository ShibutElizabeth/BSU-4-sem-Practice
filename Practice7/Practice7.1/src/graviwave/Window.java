package graviwave;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Window extends JFrame {
    private BufferedImage image;
    private JPanel panel;
    private ArrayList<Fragment> fragments;
    private BufferedImage win;
    private int n =3;
    private Window(){
        super("Practice 7.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }
        panel = new JPanel(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem show = new JMenuItem("Show image");

        file.add(open);
        file.add(exit);
        help.add(show);
        show.setEnabled(false);
        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);

        JLabel[][] labels = new JLabel[n][n];
        JPanel labelPanel;
        fragments = new ArrayList<>();
        ArrayList<Icon> icons = new ArrayList<>();
        labelPanel = new MyPanel(labels);
        Timer t2 = new Timer();

        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Файл \"JPG\"", "jpg"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Файл \"PNG\"", "png"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    image = ImageIO.read(new File(fileChooser.getSelectedFile().getPath()));
                    panel.setSize(image.getWidth(), image.getHeight());
                    setSize(image.getWidth()+50, image.getHeight()+70);
                    setResizable(false);
                    win = ImageIO.read(new File("C:\\Users\\Лиза\\IdeaProjects\\Practice7.1\\src\\winner.png"));
                    int h = image.getHeight();
                    int w = image.getWidth();
                    icons.clear();
                    fragments.clear();
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            labels[i][j].setIcon(new ImageIcon(image.getSubimage(j*(w/n), i*(h/n), w/n, h/n)));
                            labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                            icons.add(labels[i][j].getIcon());
                            fragments.add(new Fragment(labels[i][j], i, j));
                        }
                    }
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
                Timer t1 = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        ArrayList<Integer> array = getRandomNumber();
                        int f =0;
                        for(int i = 0; i < n; i++){
                            for(int j = 0; j < n; j++){
                                labels[i][j].setIcon(icons.get(array.get(f)));
                                f++;
                            }
                        }
                    }
                };
                show.setEnabled(true);
                t1.schedule(timerTask, 3000);

                TimerTask timerTask2 = new TimerTask() {
                    @Override
                    public void run() {
                        if(win(fragments, labels)){
                            JPanel winPanel = new JPanel(new BorderLayout());
                            JLabel winner = new JLabel(new ImageIcon(win));
                            winPanel.add(winner, BorderLayout.CENTER);

                            JOptionPane.showMessageDialog(panel, winPanel,
                                    "Congratulations!", JOptionPane.PLAIN_MESSAGE , null);
                            cancel();
                        }
                    }
                };
                t2.schedule(timerTask2, 6000, 100);
            }
        });
        show.addActionListener(e -> {

            JLabel helpLabel = new JLabel(new ImageIcon(image));
            JPanel inputPanel = new JPanel();
            inputPanel.add(helpLabel);
            JOptionPane.showMessageDialog(this, inputPanel,
                    "Help", JOptionPane.PLAIN_MESSAGE);
        });

        exit.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        panel.add(labelPanel);
        add(panel);
        pack();
        setSize(750, 750);
        setVisible(true);
    }
    private ArrayList<Integer> getRandomNumber() {
        ArrayList<Integer> numbersGenerated = new ArrayList<>();

        for (int i = 0; i < Math.pow(n, 2); i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt((int)Math.pow(n, 2));

            if (!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
        return numbersGenerated;
    }
    private boolean win(ArrayList<Fragment> fragments, JLabel[][]labels){
        int f =0;
        for (int i =0; i < n; i++){
            for(int j =0; j < n; j++){
                if(!(fragments.get(f).getRightPlace(i, j, labels[i][j].getIcon()))){
                    return false;
                }
                f++;
            }
        }
        return true;
    }
        public static void main(String[] args) {
        Window window = new Window();
    }
}

