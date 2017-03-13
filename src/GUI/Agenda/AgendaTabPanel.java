package GUI.Agenda;

import GUI.ColoredJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jaapj on 13-2-2017.
 */
public class AgendaTabPanel extends ColoredJPanel {

    public AgendaTabPanel() {
        add(new JScrollPane(new AgendaTable()), BorderLayout.WEST);
        //add(new )
    }

}
