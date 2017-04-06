package GUI.Simulation;

import GUI.ColoredJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jaap-Jan on 21-3-2017.
 */
public class SimulationControlPanel extends ColoredJPanel {
    public SimulationControlPanel() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = 1;
        c.gridwidth = 2;

        JSlider s = new JSlider();
        c.gridx = 0;
        c.gridy = 0;
        add(s, c);

        c.gridwidth = 1;

        JButton reset = new JButton("Reset");
        c.gridy = 1;
        add(reset, c);

        JButton playpause = new JButton("Play");
        c.gridx = 1;
        add(playpause, c);

    }
}
