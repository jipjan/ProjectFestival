package GUI;

import AI.Npc;
import Events.*;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.TiledMapReader.MyTiledJsonParser;
import NewAI.AILogic.AILogicRunner;
import de.jaret.util.date.JaretDate;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class CurrentSetup {

    public static Events Events = new Events(
        Arrays.asList(
                new Event("K3", "K3", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(90))),
                new Event("Red Tour", "Taylor Swift", (short) 2, new Time(new JaretDate(), new JaretDate().advanceMinutes(60))),
                new Event("Break Into Action", "The Nearly Dead", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(60))),
                new Event("Party Hard", "Panic! At The Disco", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(60))),
                new Event("Riot!", "Paramore", (short) 5, new Time(new JaretDate(), new JaretDate().advanceMinutes(60)))
                ));

    public static ArrayList<Npc> Npcs = new ArrayList<>(Arrays.asList(new Npc(new Point2D.Double(5.0, 5.0)), new Npc(new Point2D.Double(100.0, 100.0) {
    })));

    public static void reset() {
        Events.clear();
    }

    public static TiledMapDrawer map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1.json");

    public static AILogicRunner aiLogicRunner = new AILogicRunner(map.getObjectLayers());

    public static int Podia = aiLogicRunner.get_podia().size();

    public static int npcCount = 25;
}