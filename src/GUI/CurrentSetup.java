package GUI;

import Events.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CurrentSetup {
    public static Events Events = new Events(
        Arrays.asList(new Event("Test", "K3", (short) 5, new Time(new Date(), new Date()),1), new Event("Test", "Test", (short) 2, new Time(new Date(), new Date()), 2)));
}
