package GUI.MyPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jaapj on 6-3-2017.
 */
public class PlanningCellRenderer extends JPanel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        PlanningTableObject obj = (PlanningTableObject) value;
        setBackground(Color.PINK);
        if (hasFocus) setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        else setBorder(null);
        return this;
    }
}
