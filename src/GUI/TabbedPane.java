package GUI;

import GUI.MyPanel.ObjectTableList;
import GUI.entertainerEditor.EntertainerEditorPanel;

import java.awt.*;
import javax.swing.*;
public class TabbedPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        Events.Events eventList = new Events.Events();

        setLayout(new BorderLayout());
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());
        //setup tests------------------------------------------------------------------------

        eventList.add(new Events.Event("test1",(short) 0,new Events.Time(60)));
        eventList.add(new Events.Event("test2",(short) 2,new Events.Time(600)));
        //---------------------------------------------------------------------------------

        JPanel agenda = new JPanel(new FlowLayout());
        agenda.setName("Agenda");
        agenda.setBackground(Color.pink);
        ObjectTableList agendaObjectTable = new ObjectTableList(eventList);
        agenda.add(agendaObjectTable);
        tb.add(agenda);

        //___________________________________________________________________________________
        JPanel eventEditor = new EntertainerEditorPanel(eventList); //new JPanel(new FlowLayout());
        tb.add(eventEditor);


        //___________________________________________________________________________________
        JPanel simulation = new JPanel(new FlowLayout());
        simulation.setName("Simulation");
        simulation.setBackground(Color.pink);
        tb.add(simulation);

        //'__________________________________________________________________________________
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