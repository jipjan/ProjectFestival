package GUI;

import GUI.Agenda.AgendaTabPanel;
import GUI.EntertainerEditor.*;
import GUI.Simulation.SimulationTabPanel;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends ColoredJPanel {

    private static final long serialVersionUID = 1L;

    public MainFrame() {
        setLayout(new BorderLayout());

        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());

        tb.add(new AgendaTabPanel());
        tb.add(new EntertainerEditorTabPanel());//todo
        tb.add(new SimulationTabPanel());

        add(new TopBar(), BorderLayout.NORTH);
        add(tb, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainFrame());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1040, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}