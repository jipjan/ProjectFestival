package GUI;

import AI.TableObjectModel;
import javax.swing.*;

public class ObjectTableList<ITableObject> extends JTable {
    public ObjectTableList(TableObjectModel model) {
        setModel(model);
    }
}
