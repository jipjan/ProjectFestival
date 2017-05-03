package UnitTests.Events;


import Events.Event;
import Events.Time;
import de.jaret.util.date.JaretDate;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class EventTests {

    @Test
    public void equalsFalseTest() {
        Event a = new Event("Test", "K3", (short) 5, new Time(new JaretDate(), new JaretDate()));
        Event b = new Event("Test", "Test", (short) 2, new Time(new JaretDate(), new JaretDate()));
        assertFalse(a.equals(b));
    }
}
