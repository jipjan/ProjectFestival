package GUI.MyPanel;

import Events.Events;
import GUI.ColoredJPanel;
import GUI.EntertainerEditor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        table.setModel(new TableObjectModel(events , "","",""));
        add(table);
    }

    public Event getSelectedEvent()
    {
        return selectedEvent;
    }

}
