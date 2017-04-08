package Mapviewer.TiledMapReader.JsonClasses;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Jaap-Jan on 8-4-2017.
 */
public class TileData {
    HashMap<Integer, Integer> _tiles;

    public TileData(int size) {
        _tiles = new HashMap<>(size);
    }

    public void addTile(int x, int y, int id) {
        _tiles.put(x+y, id);
    }

    public int getTileId(int x, int y) {
        return _tiles.get(x+y);
    }
}
