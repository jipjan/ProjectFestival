package NewAI.Pathfinding;

import org.dyn4j.geometry.Vector2;

/**
 * Created by jacco on 22/03/2017.
 */
public class GridLocation {
    private static final int TILESIZE = 32;
    private int x, y;

    public GridLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridLocation(Vector2 location)
    {
        //todo debug
        x = (int) Math.round( location.x /TILESIZE);
        y = (int) Math.round( location.y /TILESIZE);

    }

    public boolean isSameGridLocation(GridLocation toCheck)
    {
        if (x == toCheck.getX() && y == toCheck.getY())
            return true;
        else return false;
    }

    public Vector2 getLocation(){
        //todo debug
        return new Vector2(x * TILESIZE, y * TILESIZE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
