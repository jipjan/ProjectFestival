package NewAI.BaseClasses;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 6-4-2017.
 */
public class MyBody extends Body {
    public BufferedImage Sprite;

    public MyBody(BufferedImage sprite, double x, double y) {
        Sprite = sprite;
        addFixture(Geometry.createSquare(5));
        setMass(MassType.NORMAL);
        translate(x, y);
    }
}
