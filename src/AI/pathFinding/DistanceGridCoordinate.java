package AI.pathFinding;

/**
 * Created by jacco on 20/03/2017.
 */
public class DistanceGridCoordinate {
    private int x;
    private int y;
    private double distance;

    public DistanceGridCoordinate(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistance() {
        return distance;
    }

}
