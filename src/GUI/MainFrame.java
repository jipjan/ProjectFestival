package GUI;

import GUI.Agenda.AgendaTabPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lois Gussenhoven, 2118259 on 6-2-2017.
 */
public class MainFrame extends JFrame{
    public static void main(String[] blah)
    {
        new MainFrame();
    }

    public MainFrame()
    {
        super("festival planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(700, 500));


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UIManager uiManager = new UIManager();
        JTabbedPane tabbedPane = new JTabbedPane();

        setContentPane(tabbedPane);
        tabbedPane.setMinimumSize(new Dimension(700, 490));

        JPanel agenda = new AgendaTabPanel();
        agenda.setName("Agenda");
        agenda.setMinimumSize(new Dimension( 700, 490));
        tabbedPane.add(agenda);

        JPanel eventEditor = new JPanel();
        eventEditor.setName("Event editor");
        tabbedPane.add(eventEditor);

        JPanel simulation = new JPanel();
        simulation.setName("Simulation");
        tabbedPane.add(simulation);

        JPanel mapEditor = new JPanel();
        mapEditor.setName("Map editor");
        tabbedPane.add(mapEditor);

        setVisible(true);
    }
}
