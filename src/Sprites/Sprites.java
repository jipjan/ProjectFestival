package Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 3-4-2017.
 */
public class Sprites {

    public static BufferedImage[] Bezoekers = new BufferedImage[10];
    public static BufferedImage Toilet;

    public static void Init() {
        for (int i = 1; i <= 10; i++)
            Bezoekers[i-1] = Loader.load("Bezoeker/Bezoeker" + i + ".png");
        Toilet = Loader.load("Bezoeker/Toilet.png");
    }
}
