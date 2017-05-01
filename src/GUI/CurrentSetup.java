package GUI;

import AI.Npc;
import Events.*;
import de.jaret.util.date.JaretDate;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class CurrentSetup {
    public static int Podia = 3;

    public static Events Events = new Events(
        Arrays.asList(
                new Event("K3 Festival", "K3", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(30))),
                new Event("Test", "Test", (short) 2, new Time(new JaretDate(), new JaretDate()))));

    public static ArrayList<Npc> Npcs = new ArrayList<>(Arrays.asList(new Npc(new Point2D.Double(5.0, 5.0)), new Npc(new Point2D.Double(100.0, 100.0) {
    })));

    public static void reset() {
        Events.clear();

    }
}