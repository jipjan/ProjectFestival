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

    /***
     * Make a MyBody object, if the sprite is null, there will be no fixture made, so you will have to add that one yourself.
     * @param sprite Sprite to use to view object
     * @param x X-location to place object
     * @param y Y-location to place object
     */
    public MyBody(BufferedImage sprite, double width, double height, double x, double y) {
        Sprite = sprite;
        addFixture(Geometry.createRectangle(width, height));
        setMass(MassType.NORMAL);
        translate(x, y);
    }

    public MyBody(double x, double y) {
        setMass(MassType.NORMAL);
        translate(x, y);
    }
}
