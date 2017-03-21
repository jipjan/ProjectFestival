package GUI.Agenda.Planning;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.*;

import Events.Event;
import ImportExport.CurrentSetup;
import de.jaret.util.date.Interval;
import de.jaret.util.date.JaretDate;
import de.jaret.util.ui.timebars.TimeBarViewerInterface;
import de.jaret.util.ui.timebars.mod.DefaultIntervalModificator;
import de.jaret.util.ui.timebars.model.*;
import de.jaret.util.ui.timebars.swing.TimeBarViewer;
import de.jaret.util.ui.timebars.swing.renderer.BoxTimeScaleRenderer;

public class SchedulingPanel extends JPanel {

    JTable _EventTable;
    EventTableModel _EventTableModel;

    TimeBarViewer _tbv;
    ScheduleTimeBarModel _model;

    protected List<Event> _draggedEvents;
    protected List<Integer> _draggedEventsOffsets;

    protected JaretDate _tbvDragOrigBegin;
    protected JaretDate _tbvDragOrigEnd;
    protected MyTimeBarRowModel _tbvDragOrigRow;

    public SchedulingPanel() {
        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.85);
        splitPane.setEnabled(false);
        add(splitPane, BorderLayout.CENTER);

        // TBV
        _model = createTBVModel();
        _tbv = new TimeBarViewer();
        _tbv.setModel(_model);
        splitPane.setTopComponent(_tbv);

        // do some configurations on the timebarviewer
        _tbv.setDrawRowGrid(true);
        _tbv.setYAxisWidth(150);
        _tbv.setTimeScaleRenderer(new BoxTimeScaleRenderer());
        _tbv.setTimeScalePosition(TimeBarViewerInterface.TIMESCALE_POSITION_TOP);
        _tbv.setInitialDisplayRange(new JaretDate().setTime(0, 0, 0, 0), 24 * 60 * 60);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                _tbv.setInitialDisplayRange(new JaretDate().setTime(0, 0, 0, 0), 24 * 60 * 60);
            }
            @Override
            public void componentMoved(ComponentEvent e) {

            }
            @Override
            public void componentShown(ComponentEvent e) {

            }
            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        _tbv.addIntervalModificator(new PreventOverlapIntervalModificator());
        _tbv.registerTimeBarRenderer(Event.class, new EventRenderer());

        setUpDND(_tbv);

        createActions(_tbv);

        // table
        _EventTableModel = new EventTableModel(CurrentSetup.Events);
        _EventTable = new JTable(_EventTableModel);
        JScrollPane scroll = new JScrollPane(_EventTable);
        splitPane.setBottomComponent(scroll);

        // add dnd support
        DragSource dragSource = DragSource.getDefaultDragSource();
        DragGestureListener dgl = new EventTableDragGestureListener();
        DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(_EventTable, DnDConstants.ACTION_MOVE,
                dgl);

        // controls at the bottom
        JPanel controlPanel = createControlPanel(24 * 60 * 60);
        add(controlPanel, BorderLayout.SOUTH);


        _tbv.setUseTitleRendererComponentInPlace(true);

    }

    /**
     * Setup some actions on the timebar viewer.
     *
     * @param tbv
     */
    private void createActions(TimeBarViewer tbv) {
        // add a popup menu for the header
        Action action = new AbstractAction("Clear work center schedule") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("run " + getValue(NAME));
                TimeBarRow row = _tbv.getPopUpInformation().getLeft();
                clearRow(row);
            }

        };
        JPopupMenu pop = new JPopupMenu("Operations");
        pop.add(action);
        _tbv.setHeaderContextMenu(pop);

        // add a popup menu for EventIntervals
        action = new AbstractAction("Unschedule") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("run " + getValue(NAME));
                unscheduleSelected();
            }
        };
        pop = new JPopupMenu("Operations");
        pop.add(action);
        _tbv.registerPopupMenu(Event.class, pop);
    }

    private void unscheduleSelected() {
        List<Interval> intervals = new ArrayList<Interval>(_tbv.getSelectionModel().getSelectedIntervals());
        for (Interval interval : intervals) {
            TimeBarRow row = _model.getRowForInterval(interval);
            ((MyTimeBarRowModel)row).remInterval(interval);
        }
    }
    private void clearRow(TimeBarRow row) {
        List<Interval> intervals = new ArrayList<Interval>(row.getIntervals());
        for (Interval interval : intervals) {
            ((MyTimeBarRowModel)row).remInterval(interval);
        }
    }


    /**
     * Setup the droptarget and the drag source on the timebar viewer.
     *
     * @param tbv
     */
    private void setUpDND(final TimeBarViewer tbv) {

        // create and setup drop target

        DropTarget dropTarget = new DropTarget();
        tbv.setDropTarget(dropTarget);

        try {
            dropTarget.addDropTargetListener(new DropTargetListener() {

                public void dropActionChanged(DropTargetDragEvent evt) {
                }

                public void drop(DropTargetDropEvent evt) {
                    if (_draggedEvents != null) {
                        TimeBarRow overRow = tbv.getRowForXY(evt.getLocation().x, evt.getLocation().y);
                        if (overRow != null) {
                            for (Event Event : _draggedEvents) {
                                ((MyTimeBarRowModel) overRow).addInterval(Event);
                            }
                            tbv.setGhostIntervals(null, null);
                            evt.dropComplete(true);
                            evt.getDropTargetContext().dropComplete(true);
                            // TODO mystic problem with drop success
                            _tbvDragOrigRow = null; // mark the drag successful ...
                        }
                        tbv.deHighlightRow();
                    }
                }

                public void dragOver(DropTargetDragEvent evt) {

                    for (Event e : _draggedEvents)
                        for (int i = 0; i < _tbv.getModel().getRowCount(); i++)
                            for (Interval inter : _tbv.getModel().getRow(i).getIntervals())
                                if (inter.equals(e)) {
                                    TimeBarRow row = _model.getRowForInterval(inter);
                                    ((MyTimeBarRowModel) row).remInterval(inter);
                                    break;
                                }


                    TimeBarRow overRow = tbv.getRowForXY(evt.getLocation().x, evt.getLocation().y);
                    if (overRow != null) {
                        tbv.highlightRow(overRow);

                        JaretDate curDate = tbv.dateForXY(evt.getLocation().x, evt.getLocation().y);
                        correctAndScheduleEvents(_draggedEvents, curDate);

                        // tell the timebar viewer
                        tbv.setGhostIntervals(_draggedEvents, _draggedEventsOffsets);
                        tbv.setGhostOrigin(evt.getLocation().x, evt.getLocation().y);
                        if (dropAllowed(_draggedEvents, overRow)) {
                            evt.acceptDrag(DnDConstants.ACTION_MOVE);
                        } else {
                            evt.rejectDrag();
                            tbv.setGhostIntervals(null, null);
                        }
                    } else {
                        tbv.deHighlightRow();
                    }
                }

                public void dragExit(DropTargetEvent evt) {
                    tbv.deHighlightRow();
                }

                public void dragEnter(DropTargetDragEvent evt) {
                }
            });
        } catch (TooManyListenersException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        // add drag source
        DragSource dragSource = DragSource.getDefaultDragSource();
        DragGestureListener dgl = new TimeBarViewerDragGestureListener();
        DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(_tbv._diagram,
                DnDConstants.ACTION_MOVE, dgl);

        dragSource.addDragSourceListener(new DragSourceListener() {

            public void dropActionChanged(DragSourceDragEvent dsde) {
                // TODO Auto-generated method stub

            }

            public void dragOver(DragSourceDragEvent dsde) {
                // TODO Auto-generated method stub

            }

            public void dragExit(DragSourceEvent dse) {
                // TODO Auto-generated method stub

            }

            public void dragEnter(DragSourceDragEvent dsde) {
                // TODO Auto-generated method stub

            }

            public void dragDropEnd(DragSourceDropEvent dsde) {
                if (!dsde.getDropSuccess() && _tbvDragOrigRow != null) {
                    // drag did not suceed -> restore original position
                    Event Event = _draggedEvents.get(0);
                    Event.setBegin(_tbvDragOrigBegin);
                    Event.setEnd(_tbvDragOrigEnd);
                    _tbvDragOrigRow.addInterval(Event);
                    _tbvDragOrigRow = null;
                }
                _tbv.setGhostIntervals(null, null);
            }
        });


    }

    class TimeBarViewerDragGestureListener implements DragGestureListener {
        public void dragGestureRecognized(DragGestureEvent e) {
            Component c = e.getComponent();
            System.out.println("component " + c);
            System.out.println(e.getDragOrigin());

            boolean markerDragging = _tbv.getDelegate().isMarkerDraggingInProgress();
            if (markerDragging) {
                return;
            }

            List<Interval> intervals = _tbv.getDelegate().getIntervalsAt(e.getDragOrigin().x, e.getDragOrigin().y);
            if (intervals.size() > 0 && e.getTriggerEvent().isAltDown()) {
                Interval interval = intervals.get(0);
                e.startDrag(null, new StringSelection("Drag " + ((Event) interval).getName()));
                _draggedEvents = new ArrayList<Event>();
                _draggedEvents.add((Event)interval);
                _draggedEventsOffsets = new ArrayList<Integer>();
                _draggedEventsOffsets.add(0);
                TimeBarRow row = _model.getRowForInterval(interval);
                ((MyTimeBarRowModel)row).remInterval(interval);

                // save orig data
                _tbvDragOrigRow = (MyTimeBarRowModel)row;
                _tbvDragOrigBegin = interval.getBegin().copy();
                _tbvDragOrigEnd = interval.getEnd().copy();

                return;
            }
//            Point origin = e.getDragOrigin();
//            if (_tbv.getDelegate().getYAxisRect().contains(origin)) {
//                TimeBarRow row = _tbv.getRowForXY(origin.x, origin.y);
//                if (row != null) {
//                    e.startDrag(null, new StringSelection("Drag ROW " + row));
//                }
//            }

        }
    }



    /**
     * Correct the dates of the dragged Events and do a simple forward scheduling.
     *
     * @param draggedEvents
     * @param curDate
     */
    private void correctAndScheduleEvents(List<Event> draggedEvents, JaretDate curDate) {
        for (int i = 0; i < draggedEvents.size(); i++) {
            Interval interval = draggedEvents.get(i);
            int secs = interval.getSeconds();
            interval.setBegin(curDate.copy());
            interval.setEnd(curDate.copy().advanceSeconds(secs));
            curDate = interval.getEnd().copy();
        }
    }

    /**
     * Check that none of the dragged Events overlaps with another interval in the row. Brute force approach.
     *
     * @param draggedEvents
     * @param row
     * @return
     */
    private boolean dropAllowed(List<Event> draggedEvents, TimeBarRow row) {
        for (Event Event : draggedEvents) {
            for (Interval interval : row.getIntervals()) {
                if (Event.intersects(interval)) {
                    return false;
                }
            }
        }

        return true;
    }

    class EventTableDragGestureListener implements DragGestureListener {
        public void dragGestureRecognized(DragGestureEvent e) {
            Component c = e.getComponent();
            System.out.println("component " + c);
            System.out.println(e.getDragOrigin());

            _draggedEvents = new ArrayList<Event>();
            _draggedEventsOffsets = new ArrayList<Integer>();
            int[] indizes = _EventTable.getSelectedRows();
            if (indizes.length > 0) {
                for (int i : indizes) {
                    _draggedEvents.add(_EventTableModel.getEvent(i));
                    _draggedEventsOffsets.add(0);
                }
                e.startDrag(null, new StringSelection("Drag " + indizes.length + " intervals"));
            }
        }
    }

    private JPanel createControlPanel(int initialSeconds) {
        JPanel panel = new JPanel();
        // simple layout
        panel.setLayout(new FlowLayout());

        // unschedule button
        final JButton unscheduleButton = new JButton("Unschedule");
        unscheduleButton.setEnabled(false);
        _tbv.getSelectionModel().addTimeBarSelectionListener(new TimeBarSelectionListener() {

            public void selectionChanged(TimeBarSelectionModel selectionModel) {
                unscheduleButton.setEnabled(selectionModel.hasIntervalSelection());
            }

            public void elementRemovedFromSelection(TimeBarSelectionModel selectionModel, Object element) {
                unscheduleButton.setEnabled(selectionModel.hasIntervalSelection());
            }

            public void elementAddedToSelection(TimeBarSelectionModel selectionModel, Object element) {
                unscheduleButton.setEnabled(selectionModel.hasIntervalSelection());
            }
        });
        unscheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                unscheduleSelected();
            }
        });
        panel.add(unscheduleButton);

        return panel;
    }

    private int calcInitialSliderVal(double c, double b, double faktor, int seconds) {

        double x = 1 / b * log2((seconds - c) / faktor);

        return (int) x;
    }

    private double log2(double a) {
        return Math.log(a) / Math.log(2);
    }

    protected ScheduleTimeBarModel createTBVModel() {
        ScheduleTimeBarModel model = new ScheduleTimeBarModel();
        for (int i = 1; i <= CurrentSetup.Podia; i++)
            model.addRow("Podium " + i);
        return model;
    }

    private class PreventOverlapIntervalModificator extends DefaultIntervalModificator {

        @Override
        public boolean newBeginAllowed(TimeBarRow row, Interval interval, JaretDate newBegin) {
            boolean result = true;
            for (Interval i : row.getIntervals()) {
                if (i != interval && i.contains(newBegin)) {
                    result = false;
                    break;
                }
            }

            return result;
        }

        @Override
        public boolean newEndAllowed(TimeBarRow row, Interval interval, JaretDate newBegin) {
            boolean result = true;
            for (Interval i : row.getIntervals()) {
                if (i != interval && i.contains(newBegin)) {
                    result = false;
                    break;
                }
            }

            return result;
        }

        @Override
        public boolean shiftAllowed(TimeBarRow row, Interval interval, JaretDate newBegin) {
            boolean result = true;
            for (Interval i : row.getIntervals()) {
                if (i != interval && i.contains(newBegin)) {
                    result = false;
                    break;
                }
            }

            return result;
        }

        @Override
        public boolean isApplicable(TimeBarRow row, Interval interval) {
            return true;
        }

    }

}

