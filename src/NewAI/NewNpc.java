package NewAI;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;
import java.util.Random;

public class NewNpc extends MyBody {

    public NewNpc(double x, double y) {
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
        addFixture(Geometry.createCircle(Sprite.getHeight()));
        setMass(MassType.NORMAL);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
         setLinearVelocity(new Vector2(getWorldCenter(), new Vector2(x, y)));
    }
}
