import Events.Event;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class AgendaGUI extends JTable
{
    AbstractTableModel model;

    public void AgendaTable(Event event)
    {
        this.setModel(model = new AbstractTableModel() {
            public int getRowCount() {
                return 0;
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex == 0)
                    return event.getName();
                else if(columnIndex == 1)
                    return event.getTime();
                else if(columnIndex == 2)
                    return event.getName();
                return null;
            }
            @Override
            public String getColumnName(int column) {
                if(column == 0)
                    return "Podium 1";
                else if(column == 1)
                    return "Podium 2";
                else if(column == 2)
                    return "Podium 3";
                return "";
            }
        });

        model.fireTableDataChanged();
    }

}
