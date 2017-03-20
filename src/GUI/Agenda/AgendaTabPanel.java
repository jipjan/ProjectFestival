package GUI.Agenda;

import GUI.Agenda.Planning.SchedulingPanel;

import javax.swing.*;
import java.awt.*;

public class AgendaTabPanel extends JPanel {

    public AgendaTabPanel() {
        super(new GridLayout(1,0));
        setName("Agenda");
        add(new JScrollPane(new SchedulingPanel()));
    }

}
