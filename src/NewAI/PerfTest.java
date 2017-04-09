package NewAI;

import Mapviewer.TiledMapReader.JsonClasses.TileData;
import NewAI.NewNotUsedPathfinder.PathfinderGrid;

import java.util.Random;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class PerfTest {

    public static void main(String[] args) {
        int[][] grid = new int[100][];
        for (int i = 0; i < 100; i++) {
            grid[i] = new int[100];
            for (int j = 0; j < 100; j++)
                grid[i][j] = 1;
        }

        TileData gridPoints = new TileData(100*100);
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                gridPoints.addTile(i, j, 1);



        int amountOfLookups = 100000;


        Random r = new Random();
        int x, y, out;

        long start = System.nanoTime();
        for (int i = 0; i < amountOfLookups; i++) {
            x = r.nextInt(100);
            y = r.nextInt(100);
            out = grid[x][y];
        }
        System.out.println("2D array: " + String.valueOf(System.nanoTime() - start));

        start = System.nanoTime();
        for (int i = 0; i < amountOfLookups; i++) {
            x = r.nextInt(100);
            y = r.nextInt(100);
            out = gridPoints.getTileId(x, y);
        }
        System.out.println("Pairing List: " + String.valueOf(System.nanoTime() - start));

    }

}
