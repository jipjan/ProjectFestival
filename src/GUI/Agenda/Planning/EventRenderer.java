package GUI.Agenda.Planning;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;

import Events.Event;
import de.jaret.util.date.Interval;
import de.jaret.util.ui.timebars.TimeBarViewerDelegate;
import de.jaret.util.ui.timebars.swing.TimeBarViewer;
import de.jaret.util.ui.timebars.swing.renderer.TimeBarRenderer;

public class EventRenderer implements TimeBarRenderer {
    protected JButton _component = new JButton();
    private JTable _table;

    public EventRenderer(JTable t) {
        _table = t;
    }

    public JComponent getTimeBarRendererComponent(TimeBarViewer tbv, Interval value, boolean isSelected,
                                                  boolean overlapping) {
        _table.tableChanged(null);
        _component.setText(value.toString());
        _component.setToolTipText(value.toString());
        if (isSelected)
            _component.setBackground(Color.CYAN);
        else
            _component.setBackground(Color.WHITE);
        return _component;
    }

    public Rectangle getPreferredDrawingBounds(Rectangle intervalDrawingArea,
                                               TimeBarViewerDelegate delegate, Interval interval,
                                               boolean selected, boolean overlap) {
        return intervalDrawingArea;
    }
}

