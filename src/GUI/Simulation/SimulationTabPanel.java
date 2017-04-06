package GUI.Simulation;

import Events.Events;
import GUI.ColoredJPanel;
import mapviewer.MapViewer;

import javax.swing.*;
import java.awt.*;


public class SimulationTabPanel extends ColoredJPanel {

    public SimulationTabPanel() {
        super(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.15);
        splitPane.setEnabled(false);
        add(splitPane, BorderLayout.CENTER);

        setName("Simulator");

        splitPane.setTopComponent(new SimulationControlPanel());
        splitPane.setBottomComponent(new MapViewer());
    }
}
