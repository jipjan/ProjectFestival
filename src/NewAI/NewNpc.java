package NewAI;

import NewAI.pathFinding.DistanceGrid;
import NewAI.pathFinding.GridLocation;
import mapviewer.tiled.TileMap;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;
import java.util.Random;
import NewAI.mood.IMood;

public class NewNpc extends MyBody {
    IMood _mood;
    DistanceGrid _finalDestination;
    GridLocation _Destination;

    public NewNpc(double x, double y, IMood mood) {
        _mood = mood;
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        addFixture(Geometry.createCircle(Sprite.getHeight()));
        setMass(MassType.NORMAL);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
        setDestination(new Vector2(x, y));
    }

    public void setDestination(Vector2 destination)
    {
        setLinearVelocity(new Vector2(getWorldCenter(), destination));
    }

    public void setFinalDestination(DistanceGrid destination)
    {
        _finalDestination = destination;
    }

    public void update(TileMap map)
    {
        if (_Destination == null)
            _Destination = _mood.giveDestination(map);
        GridLocation currentLocation = getCurrentLocation();
//        if (currentLocation.isSameGridLocation(_Destination)) {
//        }
        _Destination = _finalDestination.getNewLocation(new GridLocation(getWorldCenter()));
        setDestination(_Destination.getLocation());
    }

    public IMood get_mood() {
        return _mood;
    }

    public void set_mood(IMood _mood) {
        this._mood = _mood;
    }

    public GridLocation getCurrentLocation()
    {
        return new GridLocation(getWorldCenter());//todo debug
    }
}
