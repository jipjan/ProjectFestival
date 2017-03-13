package GUI.Agenda;

import Events.Event;
import GUI.CurrentSetup;
import GUI.MyPanel.PlanningCellRenderer;
import GUI.MyPanel.PlanningTableObject;
import GUI.MyPanel.PlanningTableObjectModel;

import javax.swing.*;

public class PlanningTable extends JTable {
    public PlanningTable(String... columns) {
        PlanningTableObjectModel<Event> m = new PlanningTableObjectModel<>(CurrentSetup.Events, columns);
        setModel(m);
        setDefaultRenderer(PlanningTableObject.class, new PlanningCellRenderer());
        setRowHeight(60);
    }
}
