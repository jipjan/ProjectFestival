package UnitTests.Events;


import Events.Event;
import Events.Time;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTests {

    @Test
    public void equalsFalseTest() {
        Event a = new Event("Test", (short) 5, new Time(new Date(), new Date()));
        Event b = new Event("Test", (short) 2, new Time(new Date(), new Date()));
        assertFalse(a.equals(b));
    }
}
