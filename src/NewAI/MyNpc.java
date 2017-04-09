package NewAI;

import NewAI.BaseClasses.MyBody;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;
import java.util.Random;

public class MyNpc extends MyBody {

    public MyNpc(double x, double y) {
        super(null, x, y);
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        BodyFixture f = new BodyFixture(Geometry.createCircle(Sprite.getWidth()));
        f.setSensor(true);
        addFixture(f);
        setMass(MassType.INFINITE);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
        setDestination(new Vector2(x, y));
    }

    public void setDestination(Vector2 destination)
    {
        Vector2 vector = new Vector2(getWorldCenter(), destination);
        rotate(0, vector);
        setLinearVelocity(vector);
    }
}
