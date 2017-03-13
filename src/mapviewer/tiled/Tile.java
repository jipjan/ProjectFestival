package mapviewer.tiled;
import java.awt.image.BufferedImage;

/**
 * Created by Thijs on 20-2-2017.
 */
public class Tile {
    private int width;
    private int height;
    private BufferedImage image;

    public Tile(int width, int height, BufferedImage tileImage)
    {
        this.width = width;
        this.height = height;
        this.image = tileImage;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public BufferedImage getTileImage()
    {
        return this.image;
    }
}
