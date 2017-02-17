package GUI.MyPanel;

import javax.swing.*;

/**
 * a class to convert the objectableList to a JTable
 */
public class ObjectTableList extends JTable {
    public ObjectTableList(TableObjectModel model) {
        setModel(model);
    }
}
