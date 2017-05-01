package NewAI;

import NewAI.BaseClasses.MyBody;
import NewAI.NewPathfinding.Grid2d;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyNpc extends MyBody {

    private List<Grid2d.MapNode> _path;

    public MyNpc(double x, double y) {
        super(null, x, y);
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        addFixture(Geometry.createCircle(Sprite.getWidth()));
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        setAutoSleepingEnabled(false);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
        setDestination(new Vector2(x, y));
    }

    public void setDestination(Vector2 destination)
    {
        Vector2 vector = new Vector2(getWorldCenter(), destination);
        transform.setRotation(vector.getDirection() + Math.PI);
        setLinearVelocity(vector.multiply(25));
    }

    private Grid2d.MapNode _cDestination;

    public void update() {
        if (_path == null)
            ; // maak hier een nieuwe path
        else {
            if (_cDestination == null)
                _cDestination = _path.get(0);

            if (inArea()) {
                setLinearVelocity(0, 0);
                _path.remove(0);
                if (_path.size() > 0) {
                    _cDestination = _path.get(0);
                } else {
                    _path = null;
                }
            }
        }
        if (_cDestination != null)
            setDestination(_cDestination.getX() * 32, _cDestination.getY() * 32);
    }

    private boolean inArea() {
        Vector2 center = getWorldCenter();
        double mX = Math.round(center.x / 32);
        double mY = Math.round(center.y / 32);
        double cX = _cDestination.getX();
        double cY = _cDestination.getY();

        return mX == cX && mY == cY;
    }



    public void setPath(List<Grid2d.MapNode> path) {
        _path = path;
    }
}
