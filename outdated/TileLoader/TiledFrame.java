package TileLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thijs_000 on 23-Feb-17.
 */
public class TiledFrame extends JPanel
{
    //Camera camera = new Camera();

    public static void main(String[] args)
    {
        JFrame content = new JFrame("TiledMap");
        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setSize(800,600);
        content.setExtendedState(Frame.MAXIMIZED_BOTH);
        content.setContentPane(new TiledFrame());
        content.setVisible(true);
    }

    public TiledFrame()
    {

    }
}
