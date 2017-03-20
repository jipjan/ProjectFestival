package GUI.MyPanel;

import java.util.Date;

/**
 * Created by jaapj on 6-3-2017.
 */
public class PlanningTableObject {
    public Date Time;
    public String[] Podia;

    public PlanningTableObject(Date t) {
        Time = t;
        Podia = new String[] { "", "", ""};
    }
}
