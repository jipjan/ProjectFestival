package ImportExport;

import Events.*;
import de.jaret.util.date.JaretDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CurrentSetup {
    public static Events Events = new Events(
        Arrays.asList(new Event("K3 Festival", "K3", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(30)),2), new Event("Test", "Test", (short) 2, new Time(new JaretDate(), new JaretDate().advanceMinutes(120)), 1)));

    public static int Podia = 3;
}
