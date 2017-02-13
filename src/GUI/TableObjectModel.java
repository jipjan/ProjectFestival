package GUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableObjectModel<TItem extends ITableObject> extends AbstractTableModel {
    private List<TItem> _items = new ArrayList<>();
    private String[] _columns;

    public TableObjectModel(String[] columns) {
        _columns = columns;
    }

    public void setItems(ArrayList<TItem> items) {
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
                return obj.getPopularity();
            default:
                return "Mistakes were made";
        }
    }

    public TItem getObject(int row) {
        return _items.get(row);
    }
}
