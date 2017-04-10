package GUI.Simulation;

import GUI.ColoredJPanel;
import Mapviewer.Mapviewer.MapViewer;

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

        MapViewer viewer = new MapViewer();
        splitPane.setTopComponent(new SimulationControlPanel(viewer));
        splitPane.setBottomComponent(viewer);
    }
}
