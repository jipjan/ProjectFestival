package GUI.MyPanel;

import Events.Time;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanningTableObjectModel<TItem extends ITableObject> extends AbstractTableModel {
    private List<PlanningTableObject> _items;
    private String[] _columns;
    SimpleDateFormat f = new SimpleDateFormat("HH:mm");

    public PlanningTableObjectModel(ArrayList<TItem> items, String... columns) {
        setTimes();
        setItems(items);
        _columns = columns;
    }

    private void setTimes() {
        _items = new ArrayList<PlanningTableObject>();
        SimpleDateFormat time = new SimpleDateFormat("mm");
        try {
            for (int i = 0; i < 48; i++)
                _items.add(new PlanningTableObject(time.parse(""+(30 * i))));
        } catch(Exception ex) {
            System.out.println("DEZE AIDS GEEFT GEEN AIDS DUMBFUCK");
        }
    }

    public void setItems(ArrayList<TItem> items) {
        for (int i = 0; i < _items.size(); i++) {
            PlanningTableObject item = _items.get(i);
            for (TItem e : items) {
                if (timeCompare(item.Time, e.getTime())) {
                    item.Podia[e.getPodium() - 1] = e.getName();
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        return _items.size();
    }

    @Override
    public int getColumnCount() {
        return _columns.length;
    }

    public String getColumnName(int col) {
        return _columns[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return f.format(_items.get(rowIndex).Time);
        else
            return _items.get(rowIndex).Podia[columnIndex - 1];
    }

    public PlanningTableObject getItem(int row) {
        return _items.get(row);
    }

    private static boolean timeCompare(Date d, Time t) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(d);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        cal.setTime(t.getBeginDate().getDate());
        int hourt1 = cal.get(Calendar.HOUR_OF_DAY);
        int minutes1 = cal.get(Calendar.MINUTE);

        cal.setTime(t.getEndDate().getDate());
        int hourt2 = cal.get(Calendar.HOUR_OF_DAY);
        int minutes2 = cal.get(Calendar.MINUTE);
        // t1 < d < t2
        return (hours >= hourt1 && hours <= hourt2 /*
                &&
                minutes >= minutes1 && minutes <= minutes2
                */);
    }
}
