package AI.mood;

import Events.Events;
import NewAI.Pathfinding.GridLocation;
import Events.Time;
import Mapviewer.TiledMapReader.JsonClasses.TileMap;

/**
 * Created by jacco on 23/03/2017.
 */
public interface IMood {
    int _likelihood = 0;//weight chance person switches to this mood;

    GridLocation giveDestination(TileMap map); //gives a destination npc wants to go to
    long timeToSpendAfterAtDestination(Time time, Events events); //gives number of tics to wait before updating to new mood or destiny
    IMood updateMood(); //gives new mood if needed;
}
