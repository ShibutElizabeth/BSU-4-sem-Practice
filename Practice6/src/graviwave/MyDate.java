package graviwave;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDate extends GregorianCalendar {
    private String record;
    private HashSet<int[]> usingList;

    MyDate(MyDate date) {
        super(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        this.record = date.getRecord();
        this.usingList = new HashSet<>();
    }

    MyDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
        this.record = getFormula();
        this.usingList = new HashSet<>();
    }

    void setHash(HashSet<int[]> set) {
        if (set!=null)
            this.usingList = set;
    }


    private String getFormula() {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        form.setCalendar(this);
        return form.format(this.getTime());
    }

    void addUsingDependency(int i, int j) {
        int[] array = new int[2];
        array[0] = i;
        array[1] = j;
        this.usingList.add(array);
    }

    HashSet getHash() {
        return usingList;
    }

    String getRecord() {
        return record;
    }

    void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
        form.setCalendar(this);
        return form.format(this.getTime());
    }
}
