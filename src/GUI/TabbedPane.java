package GUI;

import GUI.MyPanel.ObjectTableList;
import GUI.entertainerEditor.EntertainerEditorPanel;
import mapviewer.MapViewer;

import java.awt.*;
import javax.swing.*;
public class TabbedPane extends ColoredJPanel {

    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        Events.Events eventList = new Events.Events();

        setLayout(new BorderLayout());
        JPanel jp = new ColoredJPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());
        //setup tests------------------------------------------------------------------------

        eventList.add(new Events.Event("empty", "empty",(short) 0,new Events.Time(60),0));
        //---------------------------------------------------------------------------------

        JPanel agenda = new ColoredJPanel(new FlowLayout());
        agenda.setName("Agenda");
        ObjectTableList agendaObjectTable = new ObjectTableList(eventList);
        agenda.add(agendaObjectTable);
        tb.add(agenda);

        //___________________________________________________________________________________
        JPanel eventEditor = new EntertainerEditorPanel(eventList); //new JPanel(new FlowLayout());
        tb.add(eventEditor);

        //___________________________________________________________________________________
        JPanel simulation = new ColoredJPanel(new BorderLayout());
        simulation.setName("Simulation");
        simulation.add(new MapViewer());
        tb.add(simulation);

        jp.add(tb, BorderLayout.CENTER);
        add(jp, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new TabbedPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}