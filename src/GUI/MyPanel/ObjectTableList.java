package GUI.MyPanel;

import javax.swing.*;

public class ObjectTableList extends JTable {
    public ObjectTableList(AgendaTableObjectModel model) {
        setModel(model);
    }
}
