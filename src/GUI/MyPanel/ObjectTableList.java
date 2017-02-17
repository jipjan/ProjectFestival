package GUI.MyPanel;

import Events.Events;
import GUI.EntertainerEditor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a class to convert the objectableList to a JTable
 */
public class ObjectTableList extends JPanel {
    public final JTable table;
    private Event selectedEvent;

    public ObjectTableList(Events events) {
        super(new FlowLayout());
        table = new JTable();

        /*
        JButton addButton = new JButton("add");
        JButton Remove = new JButton("remove");
        add(addButton);
        add(Remove);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new TableObjectModel(events , "","",""));

            }
        });
        */
        setBackground(Color.pink);
        table.setModel(new TableObjectModel(events , "","",""));
        add(table);
    }

    public Event getSelectedEvent()
    {
        return selectedEvent;
    }

}
