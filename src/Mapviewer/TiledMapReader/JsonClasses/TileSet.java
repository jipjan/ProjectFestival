package Mapviewer.TiledMapReader.JsonClasses;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileSet {
    private int columns, firstgid;
    private String image;
    private int imageheight, imagewidth, margin;
    private String name;
    private int spacing;
    private ArrayList<TileTerrain> terrains = new ArrayList<>();
    private int tilecount, tileheight;
    private transient HashMap<Integer, BufferedImage> imageTiles;
    private transient HashMap<Integer, Short[]> tiles = new HashMap<>();
    private int tilewidth;

    public int getColumns() {
        return columns;
    }

    public int getFirstgid() {
        return firstgid;
    }

    public String getImage() {
        return image;
    }

    public int getImageheight() {
        return imageheight;
    }

    public int getImagewidth() {
        return imagewidth;
    }

    public int getMargin() {
        return margin;
    }

    public String getName() {
        return name;
    }

    public int getSpacing() {
        return spacing;
    }

    public ArrayList<TileTerrain> getTerrains() {
        return terrains;
    }

    public int getTilecount() {
        return tilecount;
    }

    public int getTileheight() {
        return tileheight;
    }

    public void addTile(int id, Short[] image) {
        tiles.put(id, image);
    }

    public BufferedImage getTile(int id) {
        if (imageTiles == null)
            generateTiles();
        return imageTiles.get(id);
    }


    // TODO; Eigenlijk dit ook naar een drawer class ipv json class.
    private void generateTiles() {
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResourceAsStream(image.replaceAll("\\.\\./", "/")));
            imageTiles = new HashMap<>();
            imageTiles.put(0, null);
            int count = 1;
            for (int y = 0; y < imageheight; y += tileheight) {
                for (int x = 0; x < imagewidth; x += tilewidth) {
                    imageTiles.put(count, tileset.getSubimage(x, y, tilewidth, tileheight));
                    count++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public HashMap<Integer, Short[]> getTiles() {
        return tiles;
    }

    public int getTilewidth() {
        return tilewidth;
    }
}
