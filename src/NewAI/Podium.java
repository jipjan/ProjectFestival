package NewAI;

import Sprites.Sprites;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

/**
 * Created by Jaap-Jan on 6-4-2017.
 */
public class Podium extends MyBody {
    public Podium(int x, int y) {
        addFixture(Geometry.createSquare(5));
        setMass(MassType.NORMAL);
        translate(x, y);
    }
}
