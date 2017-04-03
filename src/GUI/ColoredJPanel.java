package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jacco on 13/03/2017.
 */
public class ColoredJPanel extends JPanel {
    private static ArrayList<ColoredJPanel> _myPanels = new ArrayList<>();


    public ColoredJPanel()
    {
        super();
        setbg();
        _myPanels.add(this);
    }

    public ColoredJPanel(LayoutManager layoutManager)
    {
        super(layoutManager);
        setbg();
        _myPanels.add(this);
    }

    private void setbg()
    {
        setBackground(Color.pink);
    }

    public static void repaintAllPanels() {
        for(JPanel p : _myPanels)
            p.repaint();
    }
}
