package graviwave;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class View extends JFrame implements ViewInterface {
    protected static JLabel empty = new JLabel("");
    private JComboBox<String> comboBox = new JComboBox<>();
    private JTextField countryField;
    private JTextField descriptionField;
    private JTextField priceField;
    private boolean trigger;

    private final static Object[][] DATA = {
            {new Country("France").getFlagIcon(), "France is a historical center, a center of fashion, perfumery\n" +
                    " and cosmetics, wine and culinary. The dream of many tourists to visit Paris,\n" +
                    " to see the main attractions of Paris, to buy fashionable things\n" +
                    " and the latest in the perfumery market.\n" +
                    " France is a wide choice of various rest." +
                    "This trip is for you! ", 1000, false},
            {new Country("USA").getFlagIcon(), "The United States of America is a land where nations and customs\n" +
                    " mixed together, reality intertwined with fantasy.\n" +
                    " Here, the strict beauty of Niagara is replaced by the carelessness of sunny Florida,\n" +
                    " and the blue of the Pacific Ocean with the frenzy of the colors of neon lights in Las Vegas.\n" +
                    " America is not like any other country,\n" +
                    " and you need to visit it at least once in your life.", 2000, false},
            {new Country("Spain").getFlagIcon(), "What attracts tourists to Spain?\n" +
                    " Reason number one: climate. There are very few places on the planet\n" +
                    " where a person almost always feels comfortable. Spain has dozens of pristine beaches, \n" +
                    "both on the Mediterranean coast and on the Atlantic.\n" +
                    " The picturesque coastline of the peninsula, the Balearic and Canary Islands allows you \n" +
                    "to enjoy the delights of sea recreation and water sports.", 1500, false},
            {new Country("China").getFlagIcon(),
                    "China is a country that is still a mystery to many, a symbol of something far and incomprehensible.\n" +
                            " The vast territory of China is located in the expanses of Central and East Asia.\n" +
                            " The north of China is vast steppes, often covered with snow in winter,\n" +
                            " the south is tropical greenery and the warm sea. China is a country with a rich past\n" +
                            " and no less interesting present, a country that combines fidelity\n" +
                            " to the traditions of one of the oldest civilizations\n" +
                            " in the world and the modern rhythm of life in large cities.", 500, false}
    };

    private final static Object[] EMPTY_COUNTRY = {"", "TOTAL PRICE:", 0, false};
    private final static Object[] NAMES_OF_COLUMNS = {"COUNTRY", "DESCRIPTION", "PRICE", "ACTIVATE TRIP"};
    View(Controller controller) {
        super("Practice 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel unsorted = new JPanel(new BorderLayout());
        JPanel sorted = new JPanel(new BorderLayout());
        JLabel nameC = new JLabel("Country: ");
        JLabel cap = new JLabel("Capital: ");
        JTextField nameF = new JTextField();
        nameF.setPreferredSize(new Dimension(300, 30));
        nameF.setEditable(false);
        JTextField nameCap = new JTextField();
        nameCap.setPreferredSize(new Dimension(300, 30));
        nameCap.setEditable(false);
        tabbedPane.addTab("3.1", sorted);
        tabbedPane.addTab("3.2", unsorted);

        ///////////////////////////////////////////////////////////////

        sorted.add(comboBox, BorderLayout.NORTH);
        comboBox.setEnabled(false);
        JPanel left = new JPanel(new BorderLayout());
        JPanel nameCountry = new JPanel(new BorderLayout());
        nameCountry.add(nameC, BorderLayout.WEST);
        nameCountry.add(nameF, BorderLayout.CENTER);
        JPanel nameCapital = new JPanel(new BorderLayout());
        nameCapital.add(cap, BorderLayout.WEST);
        nameCapital.add(nameCap, BorderLayout.CENTER);
        left.add(nameCountry, BorderLayout.WEST);
        left.add(nameCapital, BorderLayout.EAST);

        JLabel flag = new JLabel(new ImageIcon());
        flag.setPreferredSize(new Dimension(200, 150));
        JPanel center = new JPanel();
        center.add(flag);
        sorted.add(left, BorderLayout.SOUTH);
        sorted.add(center, BorderLayout.CENTER);
        add(tabbedPane);

        //////////////////////////////////////////////////////////////////
        trigger = false;
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(500, 300));

        countryField = new JTextField();
        descriptionField = new JTextField();
        priceField = new JTextField();
        JButton addTrip = new JButton("add trip");
        JButton editTrip = new JButton("edit trip");
        JTable table = new JTable();

        JPanel panel = new JPanel(new GridLayout(3, 4));
        panel.add(new Label("COUNTRY"));
        panel.add(new Label("DESCRIPTION"));
        panel.add(new Label("PRICE"));

        panel.add(countryField);
        panel.add(descriptionField);
        panel.add(priceField);
        panel.add(addTrip);
        panel.add(editTrip);
        JPanel panel1 = new JPanel();

        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        js.setPreferredSize(new Dimension(500, 300));
        panel2.add(js);
        panel2.add(panel);

        DefaultTableModel tableModel = new DefaultTableModel(DATA, NAMES_OF_COLUMNS) {
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            public boolean isCellEditable(int row, int column) {
                if (row == this.getRowCount() - 1)
                    return false;
                else
                    return super.isCellEditable(row, column);
            }
        };
        tableModel.addRow(EMPTY_COUNTRY);

        table.setRowHeight(80);
        table.setModel(tableModel);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                for(int i =0; i <4; i++){
                    if (e.getClickCount() == 1 && target.getSelectedRow() == i && target.getSelectedColumn() == 1) {
                        JOptionPane.showMessageDialog(null, DATA[i][1]);
                    }
                }

            }
        });
        editTrip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] indexes = table.getSelectedRows();
                if (indexes.length == 1) {
                    try {
                        int price;
                        Country cntr;
                        if (!(priceField.getText().length() == 0)) {
                            price = Integer.valueOf(priceField.getText());
                            tableModel.setValueAt(price, indexes[0], 2);
                        }
                        if (!(descriptionField.getText().length() == 0)) {
                            tableModel.setValueAt(descriptionField.getText(), indexes[0], 1);
                        }
                        if (!(countryField.getText().length() == 0)) {
                            cntr = new Country(countryField.getText());
                            tableModel.setValueAt(cntr.getFlagIcon(), indexes[0], 0);
                        }
                    } catch (NumberFormatException exc) {
                        JOptionPane.showMessageDialog(null, exc.getMessage());
                    }
                }
            }
        });
        unsorted.add(panel2);
        addTrip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int price = Integer.valueOf(priceField.getText());
                    if(price < 0){
                        JOptionPane.showMessageDialog(null, "Negative price!");
                    }
                    else{
                        Country cntr = new Country(countryField.getText());
                        Object[] obj = new Object[]{cntr.getFlagIcon(), descriptionField.getText(), price, false};
                        tableModel.insertRow(tableModel.getRowCount() - 1, obj);
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage());
                }
            }
        });
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (!trigger) {
                    int sum = 0;
                    for (int i = 0; i < tableModel.getRowCount() - 1; i++) {
                        if ((Boolean) tableModel.getValueAt(i, 3)) {
                            sum += (int) tableModel.getValueAt(i, 2);
                        }
                    }
                    trigger = true;
                    tableModel.setValueAt(sum, tableModel.getRowCount() - 1, 2);
                    trigger = false;

                }
            }
        });


        /////////////////////////////////////////////////////
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(open);
        file.add(exit);
        menuBar.add(file);
        setJMenuBar(menuBar);
        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Документ XML", "xml"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getDescription(fileChooser.getSelectedFile());
                if(controller.open(filename)){
                    controller.showList();
                    comboBox.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Could not open file!");
                }
            }
        });
        exit.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        comboBox.addActionListener(e ->{
            Map<String, String> map = controller.getCapMap();
            List<Country> value = controller.getModel();
            String key = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            for (Country c : value) {
                if(map.containsKey(key)){
                    nameF.setText(key);
                    nameCap.setText(map.get(key));
                    if(c.getName().equals(key)){
                        flag.setIcon(c.getFlagIcon());
                    }

                }
            }
        });
        pack();
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void showAll(List<String> sorted){
        for (int i = 0; i < sorted.size(); i++){
            comboBox.addItem(sorted.get(i));
        }
    }

    @Override
    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }


}

