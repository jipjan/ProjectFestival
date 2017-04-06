package NewAI;

import Sprites.Sprites;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import java.awt.*;
import java.util.Random;

/**
 * Created by thijs_000 on 06-Apr-17.
 */
public class Toilet extends MyBody
{
    public Toilet(double x, double y) {
        Sprite = Sprites.Toilet;
        addFixture(Geometry.createRectangle(Sprite.getWidth(), Sprite.getHeight()));
        setMass(MassType.NORMAL);
        translate(x, y);
    }
}
