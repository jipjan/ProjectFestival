package AI;

import mapviewer.tiled.TileLayer;
import mapviewer.tiled.TileMap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jacco on 20/03/2017.
 */
public class DistanceGrid {
    //             y x
    private double[][] _distanceGrid; // double[y][x]
    private TileMap _map;
    private Queue<DistanceGridCoordinate> _pointCheckQueue;
    private int _mapSizeX;
    private int _mapSizeY;
    private static double _noValue = -2d;

    DistanceGrid(int zeroPointX, int zeroPointY, TileMap map)
    {
        _map = map;
        _mapSizeX = getMapSizeX();
        _mapSizeY = getMapSizeY();

        _pointCheckQueue = new LinkedList<>();
        _pointCheckQueue.add(new DistanceGridCoordinate(zeroPointX, zeroPointY, 0));
        generateGrid();
    }

    private void generateGrid()
    {
        //generate array for distancegrid
        _distanceGrid = new double[_mapSizeY+1][_mapSizeX+1];
        for (int i = 0; i <= _mapSizeY; i++) {
            for (int j = 0; j <= _mapSizeX; j++) {
                _distanceGrid[i][j] = _noValue;
            }
        }
        
        //fill The _distanceGrid with valid places to go
        while (!_pointCheckQueue.isEmpty())
        {
            DistanceGridCoordinate pointToCheck = _pointCheckQueue.poll();
            int pointToCheckX = pointToCheck.getX();
            int pointToCheckY = pointToCheck.getY();

            double pointToCheckDistance = pointToCheck.getDistance();
            _distanceGrid[pointToCheckY][pointToCheckX] = pointToCheckDistance;

            double newDistance = pointToCheckDistance +1;

            addNewPointToQueueIfNeeded(pointToCheckX -1, pointToCheckY, newDistance);
            addNewPointToQueueIfNeeded(pointToCheckX, pointToCheckY +1, newDistance);
            addNewPointToQueueIfNeeded(pointToCheckX +1, pointToCheckY, newDistance);
            addNewPointToQueueIfNeeded(pointToCheckX, pointToCheckY -1, newDistance);
        }
        
        //make every place not checked unAccessible
        for (int i = 0; i < _distanceGrid.length; i++)
            for (int j = 0; j < _distanceGrid[i].length; j++)
                if (_distanceGrid[i][j] < 0)
                    _distanceGrid[i][j] = -1d;
        printToConsole();
    }

    private void addNewPointToQueueIfNeeded(int x, int y, double distance)
    {
        if (isAccessible(x, y) && needsNewDistance(x,y,distance))
            _pointCheckQueue.add(new DistanceGridCoordinate(x,y, distance));
    }

    private boolean needsNewDistance(int x, int y, double currentDistance)
    {
        if (_distanceGrid[y][x] == -2 || _distanceGrid[y][x] > currentDistance)
            return true;
        return false;
    }

    boolean[][] testArray =
        {{true, true, true, true, true},
        {true, false, false, false, true},
        {true, false, true, false, true},
                {true, false, false, false, true},
        {true, true, true , true, true}};

    private boolean isAccessible(int x, int y)
    {
        //layer op kunnen vragen
        TileLayer layer = _map.getLayer();

        if (x < 0 || x >= _mapSizeX + 1 || y < 0 || y >= _mapSizeY + 1)
            return false;

        return testArray[y][x];
    }

    private void printToConsole()
    {
        for (int i = 0; i < _distanceGrid.length; i++){
            System.out.println("");
            for (int j = 0; j < _distanceGrid[i].length; j++)
                System.out.print("  " + _distanceGrid[i][j]);
        }
    }

    private int getMapSizeX()
    {
        return testArray[getMapSizeY()].length -1;
    }

    private int getMapSizeY()
    {
        return testArray.length -1;
    }

    public static void main(String[] arg)
    {
        DistanceGrid d = new DistanceGrid(0,0);
    }
}
