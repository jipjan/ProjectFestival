package GUI.Simulation;

import GUI.ColoredJPanel;
import Mapviewer.Mapviewer.MapViewer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jaap-Jan on 21-3-2017.
 */
public class SimulationControlPanel extends ColoredJPanel {
    public SimulationControlPanel(MapViewer viewer) {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JCheckBox chkDebug = new JCheckBox("Debug drawer");
        chkDebug.addActionListener((e) -> viewer.setDebug(chkDebug.isSelected()));
        c.gridx = 0;
        c.gridy = 0;
        add(chkDebug, c);

        JCheckBox chkHeatmap = new JCheckBox("Heatmap drawer");
        chkHeatmap.addActionListener((e) -> viewer.setHeatmap(chkHeatmap.isSelected()));
        c.gridy = 1;
        add(chkHeatmap, c);

        JCheckBox chkPathlayer = new JCheckBox("Pathlayer drawer");
        chkPathlayer.addActionListener((e) -> viewer.setPathlayerVisualization(chkPathlayer.isSelected()));
        c.gridy = 2;
        add(chkPathlayer, c);

        JCheckBox chkCollision = new JCheckBox("Collision");
        chkCollision.addActionListener((e) -> viewer.setCollision(chkCollision.isSelected()));
        chkCollision.setSelected(true);
        c.gridy = 3;
        add(chkCollision, c);

        c.gridwidth = 1;

        JButton reset = new JButton("Reset");
        c.gridy = 4;
        add(reset, c);

        JButton playpause = new JButton("Play");
        c.gridx = 1;
        add(playpause, c);

    }
}
