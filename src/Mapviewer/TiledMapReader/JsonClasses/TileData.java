package Mapviewer.TiledMapReader.JsonClasses;

import java.util.HashMap;

/**
 * Created by Jaap-Jan on 8-4-2017.
 */
public class TileData {
    HashMap<Double, Integer> _tiles;

    public TileData(int size) {
        _tiles = new HashMap<>(size);
    }

    public void addTile(int x, int y, int id) {
        _tiles.put(paring(x, y), id);
    }

    public int getTileId(int x, int y) {
        return _tiles.get(paring(x, y));
    }

    private double paring(int x, int y) {
        return 0.5*(x + y)*(x + y + 1) + y;
    }
}
