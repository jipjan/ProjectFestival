package NewAI;

import HelperClasses.PairingHashMap;

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

        PairingHashMap<Integer> gridPoints = new PairingHashMap(100*100);
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                gridPoints.add(i, j, 1);



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
            out = gridPoints.get(x, y);
        }
        System.out.println("Pairing List: " + String.valueOf(System.nanoTime() - start));

    }

}
