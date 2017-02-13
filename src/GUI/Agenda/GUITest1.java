package GUI.Agenda;

import Events.Time;
import GUI.MyPanel.TableObjectModel;
import GUI.MyPanel.ObjectTableList;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class GUITest1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello Java2D");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        JPanel p = new JPanel();
        TableObjectModel m = new TableObjectModel(new String[] { "Podium 1", "Podium 2", "Podium 3"});
        ObjectTableList list = new ObjectTableList(m);
        Events.Events e = new Events.Events();
        e.add(new Events.Event("Test", "K3", (short) 5, new Time(new Date(), new Date())));
        m.setItems(e);
        list.setSize(new Dimension(250, 250));
        p.add(new JScrollPane(list));
        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
