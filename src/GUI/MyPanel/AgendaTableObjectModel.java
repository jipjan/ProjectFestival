package GUI.MyPanel;

import javax.swing.table.AbstractTableModel;
import Events.Events;
import Events.Event;

public class AgendaTableObjectModel extends AbstractTableModel {
    private Events _items;
    private String[] _columns = new String[] { "Naam", "Duur", "Populariteit"};

    public AgendaTableObjectModel(Events items) {
        _items = items;
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
        ITableObject obj = _items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getName();
            case 1:
                return obj.getDuration();
            case 2:
                return obj.getPopularity();
            default:
                return "Mistakes were made";
        }
    }

    public Event getItem(int row) {
        return _items.get(row);
    }
}
