package GUI.Agenda;

import GUI.CurrentSetup;
import Events.*;
import GUI.MyPanel.TableObjectModel;

import javax.swing.*;

public class AgendaTable extends JTable {
    public AgendaTable() {
        TableObjectModel<Event> m = new TableObjectModel<>("Entertainer", "Tijd", "Populariteit");
        m.setItems(CurrentSetup.Events);
        setModel(m);
    }
}
