package GUI.MyPanel;

import Events.Events;
import GUI.ColoredJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * a class to convert the objectableList to a JTable
 */
public class ObjectTableList extends ColoredJPanel {
    public final JTable table;
    private Event selectedEvent;

    public ObjectTableList(Events events) {
        super(new FlowLayout());
        table = new JTable();
        setBackground(Color.pink);
        table.setModel(new AgendaTableObjectModel<>(events, "","",""));
        add(table);
    }

    public Event getSelectedEvent()
    {
        return selectedEvent;
    }

}
