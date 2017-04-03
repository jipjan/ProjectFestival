package GUI.Agenda.Planning;

import Events.Event;
import Events.Events;

import javax.swing.table.AbstractTableModel;

public class EventTableModel extends AbstractTableModel{

    public EventTableModel(Events events) {
        _events = events;
    }

    private Events _events = new Events();

    public Event getEvent(int index) {
        return _events.get(index);
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return _events.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Event ev = _events.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ev.getName();
            case 1:
                return ev.getPerformer();
            case 2:
                return ev.getTime().getBeginDate().toDisplayStringTime();
            case 3:
                return ev.getTime().getEndDate().toDisplayStringTime();
            case 4:
                return ev.getDuration() + " minuten";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Artist";
            case 2:
                return "Begin";
            case 3:
                return "End";
            case 4:
                return "Duration";
            default:
                return null;
        }
    }

}

