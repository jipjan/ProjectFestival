package Sprites;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 3-4-2017.
 */
public class Loader {
    public static Image load(String name) {
        try {
            return ImageIO.read(Loader.class.getResource("/" + name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
