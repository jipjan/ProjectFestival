package GUI.EntertainerEditor;

import GUI.ColoredJPanel;
import javax.swing.*;
import java.awt.*;

public class EntertainerEditorTabPanel extends ColoredJPanel {
    public EntertainerEditorTabPanel() {
        super(new GridLayout(2, 1));
        setName("Entertainer Editor");
        add(new JPanel());
        add(new EntertainerEditorPanel());
    }
}
