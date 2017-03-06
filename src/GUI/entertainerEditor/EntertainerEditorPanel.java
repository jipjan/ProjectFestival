package GUI.entertainerEditor;

import GUI.EntertainerEditor;
import GUI.MyPanel.ObjectTableList;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jacco on 20/02/2017.
 */
public class EntertainerEditorPanel extends JPanel{
    private Events.Event selectedEvent;
    private Events.Events eventList;
    private ObjectTableList eventEditotorObjectTable;
    private JPanel buttonSelectorPanel;
    private JPanel buttonPanel;
    public EntertainerEditorPanel(Events.Events eventList)
    {
        super(new FlowLayout());
        setName("Event editor");
        setBackground(Color.pink);
        this.eventList = eventList;

        if (eventList.isEmpty())
        {
            eventList.add(new Events.Event("empty", (short) 0, new Events.Time(0)));
        }
        eventEditotorObjectTable = new ObjectTableList(eventList);
        selectedEvent = eventList.get(0);

        buttonSelectorPanel = new JPanel(new GridLayout(2,1));//todo zoek alignment uit
        buttonPanel = new JPanel(new FlowLayout());

        JButton delete = new JButton("delete");
        JButton add = new JButton("add");

        buttonPanel.add(add);
        buttonPanel.add(delete);

        refreshPanel();
        //event hendelers ---------------------------------------------------------------
        eventEditotorObjectTable.table.getSelectionModel().addListSelectionListener(e -> {
            selectedEvent = eventList.get(eventEditotorObjectTable.table.getSelectedRow());
            refreshPanel();
        });

        delete.addActionListener(e -> {
            eventList.remove(selectedEvent);
            refreshPanel();
        });

        add.addActionListener(e -> {
            eventList.add(new Events.Event("",(short)0, new Events.Time(0)));
            refreshPanel();
        });
    }

    public void refreshPanel()
    {
        removeAll();
        add(buttonSelectorPanel);
        add(new GUI.EntertainerEditor(selectedEvent));
        buttonSelectorPanel.add(eventEditotorObjectTable);
        buttonSelectorPanel.add(buttonPanel);
    }
}
