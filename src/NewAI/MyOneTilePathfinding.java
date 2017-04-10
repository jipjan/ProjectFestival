package NewAI;

import HelperClasses.PairingHashMap;
import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import org.dyn4j.geometry.Vector2;

/**
 * Created by Jaap-Jan on 10-4-2017.
 */
public class MyOneTilePathfinding {

    boolean[][] _grid;
    int _tileSize;

    public MyOneTilePathfinding(TileLayer pathLayer, int tileSize) {
        _grid = new boolean[pathLayer.getWidth()][pathLayer.getHeight()];

        PairingHashMap<Integer> data = pathLayer.getData();
        for (int x = 0; x < pathLayer.getWidth(); x++)
            for (int y = 0; y < pathLayer.getHeight(); y++)
                _grid[x][y] = data.get(x, y) != 1026;
        _tileSize = tileSize;
    }

    public Vector2 tileToGoTo(Vector2 start, Vector2 end) {
        if (start.equals(end))
            return start;

        int sX = (int) start.x / 32;
        int sY = (int) start.y / 32;

        if (start.x > end.x) {
            if (_grid[sX - 1][sY])
                return start.add(-_tileSize, 0);
        } else if (start.x < end.x) {
            if (_grid[sX + 1][sY])
                return start.add(_tileSize, 0);
        }

        if (start.y > end.y) {
            if (_grid[sX][sY - 1])
                return start.add(0, -32);
        } else if (start.y < end.y) {
            if (_grid[sX][sY + 1])
                return start.add(0, -32);
        }
        return start;
    }
}
