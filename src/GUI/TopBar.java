package GUI;

import ImportExport.CurrentSetup;
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

        o.addActionListener((e) -> {
            if (f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                CurrentSetup.Events = Import.ImportJsonObject(f.getSelectedFile().getAbsolutePath(), Events.Events.class);
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
