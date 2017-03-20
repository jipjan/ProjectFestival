package GUI.EntertainerEditor;

import GUI.ColoredJPanel;
import GUI.MyPanel.ObjectTableList;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jacco on 20/02/2017.
 */
public class EntertainerEditorPanel extends ColoredJPanel{
    private Events.Event selectedEvent;
    private Events.Events eventList;
    private ObjectTableList eventEditotorObjectTable;
    private JPanel buttonSelectorPanel;
    private JPanel buttonPanel;
    public EntertainerEditorPanel(Events.Events eventList)
    {
        super(new FlowLayout());
        setName("Event editor");
        this.eventList = eventList;

        eventEditotorObjectTable = new ObjectTableList(eventList);
        selectedEvent = eventList.get(0);

        buttonSelectorPanel = new ColoredJPanel(new GridLayout(2,1));//todo zoek alignment uit
        buttonPanel = new ColoredJPanel(new FlowLayout());

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
            eventList.add(new Events.Event("","", (short)0, new Events.Time(0), 0));
            refreshPanel();
        });
    }

    public void refreshPanel()
    {
        removeAll();
        add(buttonSelectorPanel);
        add(new EntertainerEditorIets(selectedEvent));
        buttonSelectorPanel.add(eventEditotorObjectTable);
        buttonSelectorPanel.add(buttonPanel);
    }
}
