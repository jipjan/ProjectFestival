package GUI;

import GUI.CustomTabbedPaneUI;
import GUI.MyPanel.ObjectTableList;
import GUI.MyPanel.TableObjectModel;

import java.awt.*;
import javax.swing.*;

public class TabbedPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        Events.Events eventList = new Events.Events();
        Events.Events eventAgenda = new Events.Events();

        setLayout(new BorderLayout());
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());

        JPanel agenda = new JPanel(new FlowLayout());
        agenda.setName("Agenda");
        agenda.setBackground(Color.pink);
        tb.add(agenda);

        JPanel eventEditor = new JPanel(new FlowLayout());
        eventEditor.setName("Event editor");
        eventEditor.setBackground(Color.pink);
        tb.add(eventEditor);
        eventList.add(new Events.Event("empty",(short) 0,new Events.Time(60)));

        eventEditor.add(new ObjectTableList(new TableObjectModel(eventList)));
        eventEditor.add(new EntertainerEditor());

        JPanel simulation = new JPanel(new FlowLayout());
        simulation.setName("Simulation");
        simulation.setBackground(Color.pink);
        tb.add(simulation);

        JPanel mapEditor = new JPanel(new FlowLayout());
        mapEditor.setName("Map editor");
        mapEditor.setBackground(Color.pink);
        tb.add(mapEditor);


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