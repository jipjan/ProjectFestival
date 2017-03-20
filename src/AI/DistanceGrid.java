package AI;

import mapviewer.tiled.TileMap;

import java.util.ArrayList;

/**
 * Created by jacco on 20/03/2017.
 */
public class DistanceGrid
{
    public ArrayList<ArrayList<Double>> distanceGrid;
    private TileMap map;

    DistanceGrid(TileMap map)
    {
        this.map = map;
    }

    private boolean isAccesable(int x, int y)
    {
        boolean[][] testArray =
                {{true, true, true},
                {true, true, true},
                {true, true, true}};
        return testArray[y][x];
    }

    private int getMapSizeX()
    {
        return map.getWidth();
    }

    private int getMapSizeY()
    {
        return map.getHeight();
    }
}
