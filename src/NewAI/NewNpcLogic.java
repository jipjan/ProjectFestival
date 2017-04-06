package NewAI;

import Events.Events;
import Events.Time;
import mapviewer.tiled.TileMap;

/**
 * Created by jacco on 06/04/2017.
 */
public class NewNpcLogic {
    NewNpcs _npcs;
    Time _curentTime;
    TileMap _map;
    Events _events;

    NewNpcLogic(NewNpcs npcs, TileMap map, Events events)
    {
        _npcs = npcs;
        _map = map;
        _events = events;
        _curentTime = new Time();
    }

    public void update()
    {

    }
}
