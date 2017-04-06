package NewAI;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

/**
 * Created by Trist on 6-4-2017.
 */
public class NewNpc extends Body {

    public NewNpc(int x, int y) {
        addFixture(Geometry.createCircle(3));
        setMass(MassType.NORMAL);
        translate(x, y);
    }

    public void setDestination(double x, double y) {
         setLinearVelocity(new Vector2(getWorldCenter(), new Vector2(x, y)));
    }
}
