package NewAI.NewNotUsedPathfinder.Pathfinding;

import HelperClasses.PairingHashMap;
import org.dyn4j.geometry.Vector2;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class Pathfinder {
    private static final int CANWALKID = 1027;
    boolean[][] _grid;

    public Pathfinder(PairingHashMap<Integer> pathData, int width, int height) {
        _grid = new boolean[width][height];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                _grid[x][y] = pathData.get(x , y) == CANWALKID;
    }

    public Vector2 findPath(Vector2 curr, Vector2 goal) {
        /*
        if (curr.x != goal.x) {
            // need to go left or right

            //left
            if (curr.x > goal.x)
                if (_grid[(int) curr.x + 1][(int) curr.y])
                    return new Vector2(curr.x + 1, curr.y).multiply(32);
                else


        }

        if (curr.y != goal.y)
*/
        return null;
    }

}
