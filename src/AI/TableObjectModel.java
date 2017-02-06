package AI;

import GUI.ITableObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableObjectModel extends AbstractTableModel {
    private List<ITableObject> _items = new ArrayList<>();
    private String[] _columns;

    public TableObjectModel(String[] columns) {
        _columns = columns;
    }

    @Override
    public int getRowCount() {
        return _items.size();
    }

    @Override
    public int getColumnCount() {
        return _columns.length;
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
}
