package NewAI;

import GUI.CurrentSetup;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;
import NewAI.AILogic.AILogicRunner;
import NewAI.BaseClasses.MyBody;
import NewAI.NewPathfinding.Grid2d;
import Sprites.Sprites;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.util.List;
import java.util.Random;

public class MyNpc extends MyBody {

    private volatile List<Grid2d.MapNode> _path;
    private Grid2d.MapNode _cDestination;
    private Grid2d _pathfinder;
    private Thread _pathGen = new Thread();
    private final boolean _debugOn = false;
    private int _peedomiter;//pee meter peeDomiter aka how much does the npc want to pee
    private static int _peedomiterMax = 1000000;


    public MyNpc(double x, double y, Grid2d pathfinder) {
        super(x, y);
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        _pathfinder = pathfinder;

        _peedomiter = (int) (Math.random() + _peedomiterMax);

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

    private MyPoint whereDoIWantToGo() {
        if (_debugOn) System.out.println("updating whereDoIWantToGo");
        AILogicRunner aiLogicRunner = CurrentSetup.aiLogicRunner;
        TileObject ObjectToGoTo;
        if (_peedomiter > _peedomiterMax)
        {
            ObjectToGoTo = aiLogicRunner.returnRandomToilet();
            _peedomiter = 0;
        } else {
            ObjectToGoTo = aiLogicRunner.giveActualEventDestination();
        }

        if (ObjectToGoTo == null) return new MyPoint((int)Math.random()*100, (int)Math.random()*100);
        return new MyPoint(ObjectToGoTo.getX()/32, ObjectToGoTo.getY()/32);
    }

    private void generatePath() {
        MyPoint togoto = whereDoIWantToGo();
        if (inArea(togoto.x, togoto.y)) return;

        if (!_pathGen.isAlive()) {
            _pathGen = new Thread(() -> {
                int xStart = (int) getWorldCenter().x / 32;
                int yStart = (int) getWorldCenter().y / 32;
                _path = _pathfinder.findPath(xStart, yStart, togoto.x, togoto.y);
                if (_path == null && _debugOn)
                    System.out.println("No path found");
            });
            _pathGen.start();
        }
    }

    public void update() {
        _peedomiter++;
        if (_path == null|| _peedomiter > _peedomiterMax)//todo kijken of hier bugs ontstaan
            generatePath();
        else {
            if (_cDestination == null)
                _cDestination = _path.get(0);

            if (inArea(_cDestination.getX(), _cDestination.getY())) {
                setLinearVelocity(0, 0);
                _path.remove(0);
                if (_path.size() > 0)
                    _cDestination = _path.get(0);
                else
                    _path = null;
            }
        }
        if (_cDestination != null)
            setDestination(_cDestination.getX() * 32, _cDestination.getY() * 32);
    }

    private boolean inArea(int x, int y) {
        Vector2 center = getWorldCenter();
        double mX = Math.round(center.x / 32);
        double mY = Math.round(center.y / 32);
        return mX == x && mY == y;
    }

    class MyPoint {
        int x, y;

        MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
