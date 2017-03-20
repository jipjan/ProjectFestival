package GUI;

import GUI.Agenda.AgendaTabPanel;
import GUI.MyPanel.ObjectTableList;
import GUI.entertainerEditor.EntertainerEditorPanel;
import mapviewer.MapViewer;
import org.omg.CORBA.Current;

import java.awt.*;
import javax.swing.*;
public class MainFrame extends ColoredJPanel {

    private static final long serialVersionUID = 1L;

    public MainFrame() {

        setLayout(new BorderLayout());
        JPanel jp = new ColoredJPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());
        tb.add(new AgendaTabPanel());

        JPanel eventEditor = new EntertainerEditorPanel(CurrentSetup.Events); //new JPanel(new FlowLayout());
        tb.add(eventEditor);

        JPanel simulation = new ColoredJPanel(new BorderLayout());
        simulation.setName("Simulation");
        simulation.add(new MapViewer());
        tb.add(simulation);

        jp.add(tb, BorderLayout.CENTER);
        add(jp, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainFrame());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}