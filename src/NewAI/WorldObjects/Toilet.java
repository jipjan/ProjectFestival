package NewAI.WorldObjects;

import NewAI.BaseClasses.MyBody;
import Sprites.Sprites;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

public class Toilet extends MyBody
{
    public Toilet(double x, double y) {
        Sprite = Sprites.Toilet;
        addFixture(Geometry.createRectangle(Sprite.getWidth(), Sprite.getHeight()));
        setMass(MassType.NORMAL);
        translate(x, y);
    }
}
