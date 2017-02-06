package UnitTests.GUI;

import Events.Time;
import GUI.TableObjectModel;
import GUI.ObjectTableList;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class GUITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello Java2D");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        JPanel p = new JPanel();
        TableObjectModel m = new TableObjectModel(new String[] { "Test 1", "Test 2"});
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
