package graviwave;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Controller extends AbstractCellEditor implements TableCellEditor, ControllerInterface {
    private final static String MESSAGE = "EXAMPLE \"min\" or " +
            "\"max\"(dd-mm-yyyy,A4,B2), or \"=dd-mm-yyyy+-number\" or \"=CELL+-number\"";

    private Pattern Date = Pattern.compile("^(?:(?:31([/\\-.])(?:0?[13578]|1[02]))\\1" +
            "|(?:(?:29|30)(/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))" +
            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(/|-|\\.)0?2\\3" +
            "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])" +
            "|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])" +
            "(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4" +
            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
    private final static Pattern CELL = Pattern.compile("([A-Z]|[A-Z][A-Z])([1-9]|[1-9][0-9]*)");

    private JTextField editor;
    private MyDate currentValue;
    private JTable table;
    private int curRow, curCol;
    private boolean notifier = false;
    private boolean needToNotify = false;

    Controller() {
        super();
        editor = new JTextField();
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        currentValue = (MyDate) value;
        this.table = table;
        curCol = column;
        curRow = row;
        table.setToolTipText(MESSAGE);
        if (currentValue != null) {
            editor.setText(currentValue.getRecord());
            needToNotify = true;
        } else {
            editor.setText("");
            needToNotify = false;
        }
        return this.editor;
    }
    public Object getCellEditorValue() {
        MyDate date = currentValue;
        try {
            date = recordDate(editor.getText());
            if (currentValue != null)
                date.setHash(currentValue.getHash());
            currentValue = date;
            table.getModel().setValueAt(date, curRow, curCol);
            if (needToNotify) notifyTable(); }
        catch (BadInputException exc) {
            date = null;
            needToNotify = false;
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, MESSAGE);
        }
        return date;
    }


    private MyDate parseMaxOrMin(String str) throws BadInputException, NumberFormatException {
        ArrayList<MyDate> container = new ArrayList<>();
        boolean isMin;
        if (str.charAt(1) == 'i' && str.charAt(2) == 'n') {
            isMin = true;
        } else if (str.charAt(1) == 'a' && str.charAt(2) == 'x') {
            isMin = false;
        } else throw new BadInputException("Incorrect input");
        String cells = "";
        if (str.charAt(3) != '(') throw new BadInputException("Incorrect input");
        Matcher m = Date.matcher(str);
        while (m.find()) {
            cells = m.group();
            container.add(recordFullDate(cells));
        }
        cells = str.substring(str.indexOf(cells) + cells.length());
        m = CELL.matcher(cells);
        while (m.find()) {
            MyDate myDate = recordCellDate(m.group());
            if (myDate == null) continue;
            container.add(myDate);
        }
        if (cells.charAt(cells.length() - 1) != ')') throw new BadInputException("Incorrect input");

        if (isMin){
            return Collections.min(container);
        }
        else return Collections.max(container);
    }

    @Override
    public String parseMin(ArrayList<MyDate> container) throws BadInputException, NumberFormatException {
        for(MyDate date : container){
            while (date == null){
                container.remove(date);
            }
        }
        return Collections.min(container).toString();
    }
    @Override
    public String parseMax(ArrayList<MyDate> container) throws BadInputException, NumberFormatException {
        for(MyDate date : container){
            while (date == null){
                container.remove(date);
            }
        }
        return Collections.max(container).toString();
    }

    private MyDate parseEqualFormula(String str) throws BadInputException, NumberFormatException {
        MyDate first;
        int second;
        boolean isMinus;
        if (str.charAt(1) >= '1' && str.charAt(1) <= '9') {
            String usualDate = str.substring(1, 11);
            Matcher m = Date.matcher(usualDate);
            if (m.matches()) {
                first = recordFullDate(usualDate);
                second = Integer.valueOf(str.substring(12));
                if (str.charAt(11) == '+') isMinus = false;
                else if (str.charAt(11) == '-') isMinus = true;
                else throw new BadInputException("Incorrect input");
                if (isMinus) second *= -1;
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else throw new BadInputException("Incorrect input");
        } else if (str.charAt(1) >= 'A' && str.charAt(1) <= 'Z') {
            int ind = str.indexOf('-');
            isMinus = true;
            if (ind == -1) {
                ind = str.indexOf('+');
                isMinus = false;
            }
            if (ind == -1) throw new BadInputException("Incorrect input");
            String cell = str.substring(1, ind);
            Matcher m = CELL.matcher(cell);
            if (m.matches()) {
                first = recordCellDate(cell);
                if (first == null) throw new BadInputException("Incorrect input");
                second = Integer.valueOf(str.substring(cell.length() + 2));
                if (isMinus) second *= -1;
                first.add(Calendar.DAY_OF_MONTH, second);
                return first;
            } else throw new BadInputException("Incorrect input");
        } else throw new BadInputException("Incorrect input");
    }

    private MyDate recordFullDate(String str) throws NumberFormatException {
        String delimiter = "[-]";
        String[] subString = str.split(delimiter);
        int day, month, year;
        day = Integer.valueOf(subString[0]);
        month = Integer.valueOf(subString[1]);
        year = Integer.valueOf(subString[2]);
        return new MyDate(year, month-1, day);
    }

    private MyDate recordUsualDate(String str) throws NumberFormatException, BadInputException {
        Matcher m = Date.matcher(str);
        if (m.matches()) {
            return recordFullDate(str);
        } else throw new BadInputException("Incorrect input");
    }

    private MyDate recordCellDate(String str) throws NumberFormatException, BadInputException {
        int curCol = 1, curRow=1;
        if(str.length()==2){
            curCol = str.charAt(0) - 'A' + 1;
            curRow = Integer.valueOf(str.substring(1)) - 1;
        }
        else if(str.length()==3){
            curCol = str.charAt(0) - 'A'  + 27;
            curRow = Integer.valueOf(str.substring(2)) - 1;
        }

        if (curRow >= table.getModel().getRowCount() || curCol >= table.getModel().getColumnCount())
            throw new BadInputException("Incorrect input");
        if (!notifier) {
            if (curRow == this.curRow && curCol == this.curCol)
                throw new BadInputException("Incorrect input");
        }
        if (table.getValueAt(curRow, curCol) == null) throw new BadInputException("Incorrect input");
        MyDate cellDate = (MyDate) table.getValueAt(curRow, curCol);
        if (cellDate == null)
            throw new BadInputException("Incorrect input");
        MyDate date = new MyDate(cellDate);
        if (!notifier) {
            date.addUsingDependency(this.curRow, this.curCol);
        }
        return date;
    }

    private MyDate recordDate(String str) throws NumberFormatException, BadInputException {
        MyDate myDate = currentValue;
        if (str.length() >= 5) {
            switch (str.charAt(0)) {
                case 'm': {
                    myDate = parseMaxOrMin(str);
                    myDate.setRecord(str);
                    break;
                }
                case '=': {
                    myDate = parseEqualFormula(str);
                    myDate.setRecord(str);
                    break;
                }
                default: {
                    myDate = recordUsualDate(str);
                    myDate.setRecord(str);
                }
            }
        }
        else if(str.length() < 5 && str.length() >=1)
            throw new BadInputException("Incorrect input");
        return myDate;
    }

    private void notifyCell(MyDate cell, int i, int j) throws BadInputException {
        HashSet<int[]> dependencies = cell.getHash();
        MyDate tempCell = cell;

        cell = recordDate(cell.getRecord());
        cell.setHash(tempCell.getHash());
        table.setValueAt("", i, j);
        for (var elem : dependencies) {
            notifyCell((MyDate) table.getModel().getValueAt(elem[0], elem[1]), elem[0], elem[1]);
        }
    }


    private void notifyTable() throws BadInputException, NumberFormatException {
        notifier = true;
        MyDate cell;
        HashSet<int[]> dependencies = currentValue.getHash();
        for (var elem : dependencies) {
            cell = (MyDate) table.getModel().getValueAt(elem[0], elem[1]);
            notifyCell(cell, elem[0], elem[1]);
        }
        notifier = false;
    }
}
