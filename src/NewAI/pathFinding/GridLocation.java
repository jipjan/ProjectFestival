package NewAI.pathFinding;

import java.awt.geom.Point2D;

/**
 * Created by jacco on 22/03/2017.
 */
public class GridLocation {
    private int x, y;

    public GridLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridLocation(Point2D p2d)
    {
        //todo
    }

    public Point2D getLocation(){
        //todo
        return new Point2D.Double(-1, -1);
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
