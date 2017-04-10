package NewAI.NewNotUsedPathfinder;

import HelperClasses.PairingHashMap;
import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import org.dyn4j.geometry.Vector2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class PathfinderGrid {
    private static final int PATHID = 1026;

    boolean[][] _grid;

    public PathfinderGrid(TileLayer pathLayer) {
        _grid = new boolean[pathLayer.getWidth()][pathLayer.getHeight()];

        PairingHashMap<Integer> data = pathLayer.getData();
        for (int x = 0; x < pathLayer.getWidth(); x++)
            for (int y = 0; y < pathLayer.getHeight(); y++)
                _grid[x][y] = data.get(x, y) == PATHID;
    }

    public Queue<Vector2> getPathToLocation(int x, int y) {
        Queue<Vector2> path = new LinkedList<>();



        return null;
    }
}
