package ImportExport;

import Events.*;
import de.jaret.util.date.JaretDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CurrentSetup {
    private static Events _events = new Events(
        Arrays.asList(new Event("K3 Festival", "K3", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(30))), new Event("Test", "Test", (short) 2, new Time(new JaretDate(), new JaretDate().advanceMinutes(120)))));

    public static int Podia = 3;

    public static Events getEvents() {
        return _events;
    }

    public static void setEvents(Events e) {
        _events.clear();
        _events.ensureCapacity(e.size());
        _events.addAll(e);
    }

    public static void reset() {
        _events.clear();
        Podia = 0;
    }
}
