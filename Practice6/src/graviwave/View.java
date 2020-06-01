package graviwave;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class View extends JFrame  {
    private JTextField result = new JTextField();
    private JTable table;
    private DefaultTableModel tableModel;
    private int columnNumber;
    private int rowNumber;
    private final static char columnName = 'A';
    private View(){
        super("Excel lite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                else
                    return super.isCellEditable(row, column);
            }

            public Class getColumnClass(int column) {
                return MyDate.class;
            }
        };
        JPanel panel = new JPanel(new BorderLayout());
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane js = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js.setVisible(true);
        panel.add(js, BorderLayout.CENTER);
        panel.add(result, BorderLayout.NORTH);
        result.setEditable(false);

        Vector<Integer> v = new Vector<>();
        int startNumber = 11;
        rowNumber = startNumber;
        columnNumber = startNumber+10;
        for (int i = 1; i <= rowNumber-1; i++) {
            v.add(i);
        }
        table.getTableHeader().setReorderingAllowed(false);
        tableModel.addColumn("", v);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        for (int i = 0; i < columnNumber; i++) {
            tableModel.addColumn((char) (columnName + i));
            table.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        Controller controller = new Controller();
        table.setDefaultEditor(MyDate.class, controller);


        table.setSelectionBackground(new Color(99, 207, 117));
        table.setCellSelectionEnabled(true);

        ButtonGroup group = new ButtonGroup();
        JRadioButton min = new JRadioButton("min");
        JRadioButton max = new JRadioButton("max");
        group.add(min);
        group.add(max);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(min);
        buttonPanel.add(max);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        ArrayList<MyDate> dates = new ArrayList<>();
        ListSelectionModel selModel = table.getSelectionModel();
        selModel.addListSelectionListener(e -> {
            dates.clear();
            StringBuilder res = new StringBuilder();
            int[] selectedRows = table.getSelectedRows();
            int[] selectedColumns = table.getSelectedColumns();
            for (int selectedRow : selectedRows) {
                for (int selectedColumn : selectedColumns) {
                    TableModel model = table.getModel();
                    MyDate value = (MyDate) model.getValueAt(selectedRow, selectedColumn);
                    if(value != null){ dates.add(value); }
                }
            }
            for(int i = 0; i < dates.size(); i++){
                if(dates.get(i) != null){
                    res.append(dates.get(i).toString());
                    if(i != dates.size()-1){ res.append(","); }
                }
            }
            result.setText(res.toString());
            min.addActionListener(e2 -> {
                if(min.isSelected()){
                    result.removeAll();
                    result.setText(controller.parseMin(dates));
                }
            });
            max.addActionListener(e1 -> {
                if(max.isSelected()){
                    result.removeAll();
                    result.setText(controller.parseMax(dates));
                }
            });
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem row = new JMenuItem("edit rows");
        JMenuItem col = new JMenuItem("edit columns");
        options.add(col);
        options.add(row);
        menuBar.add(options);
        setJMenuBar(menuBar);

        col.addActionListener(e ->{
            JTextField org = new JTextField(20);
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Count:"));
            inputPanel.add(org);
            if (JOptionPane.showConfirmDialog(this, inputPanel,
                    "Edit the number of columns", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    int count = Integer.valueOf(org.getText());
                    if(Math.abs(count) < rowNumber) {
                        columnNumber += count;
                        tableModel.setColumnCount(columnNumber);
                        tableModel.setRowCount(rowNumber);
                        table.setModel(tableModel);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect input");
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage());
                }
            }
        });
        row.addActionListener(e ->{
            JTextField org = new JTextField(20);
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Count:"));
            inputPanel.add(org);
            if (JOptionPane.showConfirmDialog(this, inputPanel,
                    "Edit the number of rows", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    int count = Integer.valueOf(org.getText());
                    if(Math.abs(count) < rowNumber){
                        rowNumber +=count;
                        tableModel.setRowCount(rowNumber);
                        v.clear();
                        tableModel.setValueAt(1, 1, 0);
                        for(int i = 1; i < rowNumber; i++){
                            tableModel.setValueAt(i+1, i, 0);
                            table.setModel(tableModel);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect input");
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null, exc.getMessage());
                }
            }
        });

        add(panel);
        pack();
        setSize(800, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        View view = new View();
    }
}
