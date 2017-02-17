package GUI;

import GUI.Agenda.AgendaTabPanel;
import GUI.CustomTabbedPaneUI;
import GUI.MyPanel.ObjectTableList;
import GUI.MyPanel.TableObjectModel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

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
        agenda.add(new ObjectTableList(eventList));
        tb.add(agenda);

        JPanel eventEditor = new JPanel(new FlowLayout());
        eventEditor.setName("Event editor");
        eventEditor.setBackground(Color.pink);
        tb.add(eventEditor);
        eventList.add(new Events.Event("test1",(short) 0,new Events.Time(60)));
        eventList.add(new Events.Event("test2",(short) 2,new Events.Time(600)));

        ObjectTableList objectTable = new ObjectTableList(eventList);
        eventEditor.add(objectTable);
        eventEditor.add(new EntertainerEditor(eventList.get(0)));
        objectTable.table.getSelectionModel().addListSelectionListener(e -> {
            eventEditor.removeAll();
            eventEditor.add(objectTable);
            Events.Event newEvent = eventList.get(objectTable.table.getSelectedRow());
            eventEditor.add(new EntertainerEditor(newEvent));
        });

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