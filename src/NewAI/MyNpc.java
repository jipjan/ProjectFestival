package NewAI;

import AI.pathFinding.DistanceGrid;
import AI.pathFinding.GridLocation;
import NewAI.BaseClasses.MyBody;
import javafx.geometry.Point2D;
import Mapviewer.Tiled.TileMap;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;
import java.util.Random;
import AI.mood.IMood;

public class MyNpc extends MyBody {

    public MyNpc(double x, double y) {
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        addFixture(Geometry.createCircle(Sprite.getHeight()));
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
        setDestination(new Vector2(x, y));
    }

    public void setDestination(Vector2 destination)
    {
        setLinearVelocity(new Vector2(getWorldCenter(), destination));
    }
}
