package GUI;

import GUI.CustomTabbedPaneUI;

import java.awt.*;
import javax.swing.*;

public class TabbedPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        setLayout(new BorderLayout());
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());

        JPanel agenda = new JPanel();
        agenda.setName("Agenda");
        agenda.setBackground(Color.pink);
        tb.add(agenda);

        JPanel eventEditor = new JPanel();
        eventEditor.setName("Event editor");
        eventEditor.setBackground(Color.pink);
        tb.add(eventEditor);

        JPanel simulation = new JPanel();
        simulation.setName("Simulation");
        simulation.setBackground(Color.pink);
        tb.add(simulation);

        JPanel mapEditor = new JPanel();
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
        frame.setVisible(true);
    }
}