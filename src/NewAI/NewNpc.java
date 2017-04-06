package NewAI;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import Sprites.*;
import java.awt.*;
import java.util.Random;

public class NewNpc extends Body {

    public final Image Sprite;

    public NewNpc(int x, int y) {
        addFixture(Geometry.createCircle(3));
        setMass(MassType.NORMAL);
        translate(x, y);
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
    }

    public void setDestination(double x, double y) {
         setLinearVelocity(new Vector2(getWorldCenter(), new Vector2(x, y)));
    }
}
