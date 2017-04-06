package GUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jacco on 13/03/2017.
 */
public class ColoredJPanel extends JPanel {
    private static ArrayList<ColoredJPanel> _myPanels = new ArrayList<>();
    private static ArrayList<AbstractTableModel> _myJtables = new ArrayList<>();
    private static Runnable _unscheduler;

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

    public void addModel(AbstractTableModel m) {
        _myJtables.add(m);
    }

    public void setUnscheduler(Runnable r) {
        _unscheduler = r;
    }

    private void setbg()
    {
        setBackground(Color.pink);
    }

    public static void repaintAllPanels() {
        for (AbstractTableModel t : _myJtables)
            t.fireTableDataChanged();

        _unscheduler.run();

        for(JPanel p : _myPanels) {
            p.repaint();
        }
    }
}
