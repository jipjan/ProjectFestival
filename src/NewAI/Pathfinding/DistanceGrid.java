package NewAI.Pathfinding;


import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileMap;

import java.util.ArrayList;
import java.util.HashMap;
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

    public DistanceGrid(int zeroPointX, int zeroPointY, TileMap map)
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

        HashMap<Integer, DistanceGridCoordinate> checkedPlaces = new HashMap<>();
        //fill The _distanceGrid with valid places to go
        while (!_pointCheckQueue.isEmpty())
        {
            DistanceGridCoordinate pointToCheck = _pointCheckQueue.poll();
            DistanceGridCoordinate checkedPlace = checkedPlaces.get( pointToCheck.getX() * pointToCheck.getY());
            if (checkedPlace != null) {
                if (checkedPlace.getX() == pointToCheck.getX() && checkedPlace.getY() == pointToCheck.getX())
                    System.out.println("checked twice");
                else {
                    checkedPlaces.put(pointToCheck.getX() * pointToCheck.getY(), pointToCheck);

                    int pointToCheckX = pointToCheck.getX();
                    int pointToCheckY = pointToCheck.getY();
                    //System.out.println(""+ pointToCheckX+ " - " +pointToCheckY);

                    double pointToCheckDistance = pointToCheck.getDistance();
                    _distanceGrid[pointToCheckY][pointToCheckX] = pointToCheckDistance;

                    double newDistance = pointToCheckDistance + 1;

                    addNewPointToQueueIfNeeded(pointToCheckX - 1, pointToCheckY, newDistance);
                    addNewPointToQueueIfNeeded(pointToCheckX, pointToCheckY + 1, newDistance);
                    addNewPointToQueueIfNeeded(pointToCheckX + 1, pointToCheckY, newDistance);
                    addNewPointToQueueIfNeeded(pointToCheckX, pointToCheckY - 1, newDistance);
                }
            }
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
        TileLayer layer = _map.getTileLayers().get(0);

        if (x < 0 || x >= _mapSizeX + 1 || y < 0 || y >= _mapSizeY + 1)
            return false;
        if(layer.getData().get(x, y) == 1026)
            return false;

        return true;
    }

    private void printToConsole()
    {
        for (int i = 0; i < _distanceGrid.length; i++){
            System.out.println("");
            for (int j = 0; j < _distanceGrid[i].length; j++)
                System.out.print("  " + _distanceGrid[i][j]);
        }
    }

    public GridLocation getNewLocation(GridLocation currentLocation)
    {
        int currentX = currentLocation.getX();
        int currentY = currentLocation.getY();
        double currentDistance = _distanceGrid[currentY][currentX];
        double pointToCheck = 0;

        if (!isAccessible(currentX,currentY)) {//is op non accsssible terain
            ArrayList<DistanceGridCoordinate> directions = new ArrayList<>();
            directions.add(checkForAccessInLine(currentX,currentY,+1,0,0));
            directions.add(checkForAccessInLine(currentX,currentY,-1,0,0));
            directions.add(checkForAccessInLine(currentX,currentY,0,+1,0));
            directions.add(checkForAccessInLine(currentX,currentY,0,-1,0));
            directions.add(checkForAccessInLine(currentX,currentY,-1,-1,0));
            directions.add(checkForAccessInLine(currentX,currentY,+1,+1,0));
            directions.add(checkForAccessInLine(currentX,currentY,+1,-1,0));
            directions.add(checkForAccessInLine(currentX,currentY,-1,+1,0));

            DistanceGridCoordinate lowestFound = null;
            for (DistanceGridCoordinate d: directions) {
                if (d != null) {
                    lowestFound = d;
                    break;
                }
            }

            for (DistanceGridCoordinate d: directions)
            {
                if (d != null){
                    if (d.getDistance() < d.getDistance())
                        lowestFound = d;
                }
            }
            try{
                return new GridLocation(lowestFound.getX(), lowestFound.getY());
            } catch (NullPointerException e){
                System.out.println("npc Stuck! ... GO HELP HIM ASAP");
            }
        }

        if (currentY +1 <=_mapSizeY) {
            pointToCheck = _distanceGrid[currentY + 1][currentX];
            if (pointToCheck < currentDistance && pointToCheck >= 0)
                return new GridLocation(currentY + 1, currentX);
        }

        if (currentX + 1 <= _mapSizeX) {
            pointToCheck = _distanceGrid[currentY][currentX + 1];
            if (pointToCheck < currentDistance && pointToCheck >= 0)
                return new GridLocation(currentY, currentX);
        }

        if (currentY -1 >= 0) {
            pointToCheck = _distanceGrid[currentY - 1][currentX];
            if (pointToCheck < currentDistance && pointToCheck >= 0)
                return new GridLocation(currentY - 1, currentX);
        }

        if (currentX -1 >=0) {
            pointToCheck = _distanceGrid[currentY][currentX - 1];
            if (pointToCheck < currentDistance && pointToCheck >= 0)
                return new GridLocation(currentY, currentX);
        }

        return currentLocation;
    }

    private DistanceGridCoordinate checkForAccessInLine(int currentX, int currentY, int transformX, int transformY, int count)
    {
        currentX += transformX;
        currentY += transformY;
        if (_distanceGrid[currentY][currentX]>=0)
            return new DistanceGridCoordinate(currentX, currentY, count);
        if (currentX >= _mapSizeX || currentX >0 ||currentY >= _mapSizeY || currentY >0 )
            return null;
        return checkForAccessInLine(currentX,currentY ,transformX,transformY, count +1);
    }

    private int getMapSizeX()
    {
        //return testArray[getMapSizeY()].length -1;
        return _map.getWidth();
    }

    private int getMapSizeY()
    {
        //return testArray.length -1;
        return  _map.getHeight();
    }

    public static void main(String[] arg)
    {
        DistanceGrid d = new DistanceGrid(0,0, null);//put map here instead of null
    }
}

