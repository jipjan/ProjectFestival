package GUI;

import ImportExport.Export;
import ImportExport.Import;

import javax.swing.*;
import java.awt.*;

public class TopBar extends ColoredJPanel {

    public TopBar() {
        super(new FlowLayout());
        JButton n = new JButton("Nieuw");
        JButton o = new JButton("Open");
        JButton s = new JButton("Save");

        JFileChooser f = new JFileChooser();

        n.addActionListener((e) -> { CurrentSetup.reset();
        ColoredJPanel.repaintAllPanels();
        });

        o.addActionListener((e) -> {
            if (f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Events.Events aids = Import.ImportJsonObject(f.getSelectedFile().getAbsolutePath(), Events.Events.class);
                CurrentSetup.Events.clear();
                for (Events.Event evt : aids)
                    CurrentSetup.Events.add(evt);
                ColoredJPanel.repaintAllPanels();
            }
        });

        s.addActionListener((e) -> {
            if (f.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                Export.ExportJsonFile(f.getSelectedFile().getAbsolutePath(), CurrentSetup.Events);
            }
        });

        add(n);
        add(o);
        add(s);
    }
}
