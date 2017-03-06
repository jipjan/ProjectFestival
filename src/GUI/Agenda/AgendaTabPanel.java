package GUI.Agenda;

import javax.swing.*;
import java.awt.*;

public class AgendaTabPanel extends JPanel {

    public AgendaTabPanel() {
        super(new GridLayout(1,1));
        add(new JScrollPane(new AgendaTable("Entertainer", "Tijd (in minuten)", "Populariteit")));
        add(new JScrollPane(new PlanningTable("Tijd", "Podium 1", "Podium 2", "Podium 3")));
    }

}
