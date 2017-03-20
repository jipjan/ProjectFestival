package GUI.Agenda.Planning;

import de.jaret.util.ui.timebars.model.DefaultRowHeader;
import de.jaret.util.ui.timebars.model.DefaultTimeBarModel;
import de.jaret.util.ui.timebars.model.DefaultTimeBarRowModel;

public class ScheduleTimeBarModel extends DefaultTimeBarModel {

    public DefaultTimeBarRowModel addRow(String title) {
        DefaultTimeBarRowModel row = new DefaultTimeBarRowModel(new DefaultRowHeader(title));
        addRow(row);
        return row;
    }

}
