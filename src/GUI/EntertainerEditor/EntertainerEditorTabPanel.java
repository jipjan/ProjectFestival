package GUI.EntertainerEditor;

import Events.Event;
import GUI.ColoredJPanel;
import GUI.MyPanel.AgendaTableObjectModel;
import ImportExport.CurrentSetup;

import javax.swing.*;
import java.awt.*;

public class EntertainerEditorTabPanel extends ColoredJPanel {
    public EntertainerEditorTabPanel() {
        super(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.85);
        splitPane.setEnabled(false);
        add(splitPane, BorderLayout.CENTER);

        setName("Entertainer Editor");

        AgendaTableObjectModel m = new AgendaTableObjectModel(CurrentSetup.Events);
        JTable table = new JTable(m);


        EntertainerEditorPanel p = new EntertainerEditorPanel(table);

        table.getSelectionModel().addListSelectionListener((e) ->
                {
                    if (!e.getValueIsAdjusting())
                        p.setEvent(m.getItem(table.getSelectedRow()));
                }
        );

        splitPane.setTopComponent(new JScrollPane(table));
        splitPane.setBottomComponent(p);
    }
}
