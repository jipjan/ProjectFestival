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
public class Toilet extends Body
{

    public final Image Sprite;

    public Toilet(double x, double y) {
        addFixture(Geometry.createRectangle(3.2,3.2));
        setMass(MassType.INFINITE);
        translate(x, y);
        // hier moet dan de sprite van het toilet komen.
        Sprite = Sprites.Bezoekers[new Random().nextInt(Sprites.Bezoekers.length)];
    }

}
