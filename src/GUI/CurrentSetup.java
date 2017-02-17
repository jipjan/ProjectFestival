package GUI;

import Events.*;

import java.util.Date;

public class CurrentSetup {
    public static Events Events = new Events() {
        Event a = new Event("Test", "K3", (short) 5, new Time(new Date(), new Date()),1);
        Event b = new Event("Test", "Test", (short) 2, new Time(new Date(), new Date()), 2);
    };
}
