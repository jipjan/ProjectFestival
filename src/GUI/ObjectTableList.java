package GUI;

import javax.swing.*;

public class ObjectTableList<ITableObject> extends JTable {
    public ObjectTableList(TableObjectModel model) {
        setModel(model);
    }
}
