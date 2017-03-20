package GUI.Agenda;
import javax.swing.*;
import java.awt.*;


public class GUITest1 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello Java2D");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        JPanel p = new JPanel();
        Agenda agenda = new Agenda();
        JScrollPane j = new JScrollPane();
        p.add(agenda);
        //p.add(j);
        frame.setContentPane(p);
        frame.setVisible(true);

    }
}
