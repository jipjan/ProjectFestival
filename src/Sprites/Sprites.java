package Sprites;

import java.awt.*;

/**
 * Created by Jaap-Jan on 3-4-2017.
 */
public class Sprites {

    public static Image[] Bezoekers = new Image[10];

    public static void Init() {
        for (int i = 1; i <= 10; i++)
            Bezoekers[i-1] = Loader.load("Bezoeker/Bezoeker" + i + ".png");
    }
}
