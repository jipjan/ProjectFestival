package GUI.Agenda.Planning;


import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;

import Events.Event;
import de.jaret.util.date.Interval;
import de.jaret.util.ui.timebars.TimeBarViewerDelegate;
import de.jaret.util.ui.timebars.swing.TimeBarViewer;
import de.jaret.util.ui.timebars.swing.renderer.TimeBarRenderer;

public class EventRenderer implements TimeBarRenderer {
    /** component used for rendering. */
    protected JButton _component = new JButton();


    /**
     * {@inheritDoc}
     */
    public JComponent getTimeBarRendererComponent(TimeBarViewer tbv, Interval value, boolean isSelected,
                                                  boolean overlapping) {

        _component.setText(value.toString());
        _component.setToolTipText(value.toString());

        Event ev = (Event)value;

        if (isSelected) {
            _component.setBackground(Color.BLUE);
        }
        return _component;
    }

    /**
     * {@inheritDoc} Simple default implementation.
     */
    public Rectangle getPreferredDrawingBounds(Rectangle intervalDrawingArea,
                                               TimeBarViewerDelegate delegate, Interval interval,
                                               boolean selected, boolean overlap) {
        return intervalDrawingArea;
    }
}

