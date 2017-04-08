package Mapviewer.TiledMapReader.JsonClasses;

import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 8-4-2017.
 */
public class TileSets extends ArrayList<TileSet> {
    public TileSet getTileSetByGid(int gid) {
        for (TileSet t : this)
            if (t.getFirstgid() == gid)
                return t;
        return null;
    }
}
