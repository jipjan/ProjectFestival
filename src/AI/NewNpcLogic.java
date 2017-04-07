package AI;

import Events.Events;
import Events.Time;
import Mapviewer.Tiled.TileMap;
import NewAI.BaseClasses.MyNpcs;

/**
 * Created by jacco on 06/04/2017.
 */
public class NewNpcLogic {
    MyNpcs _npcs;
    Time _curentTime;
    TileMap _map;
    Events _events;

    public NewNpcLogic(MyNpcs npcs, TileMap map)
    {
        _npcs = npcs;
        _map = map;
    }

    public void update()
    {

    }
}
