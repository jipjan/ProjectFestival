package GUI.Agenda;

import ImportExport.CurrentSetup;
import Events.*;
import GUI.MyPanel.AgendaTableObjectModel;

import javax.swing.*;

public class AgendaTable extends JTable {
    public AgendaTable(String... columns) {
        AgendaTableObjectModel<Event> m = new AgendaTableObjectModel<>(CurrentSetup.Events, columns);
        setModel(m);
    }
}
