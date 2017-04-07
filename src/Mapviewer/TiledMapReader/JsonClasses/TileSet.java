package Mapviewer.TiledMapReader.JsonClasses;

import java.awt.image.BufferedImage;
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
    private transient HashMap<Integer, BufferedImage> tiles = new HashMap<>();
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

    public void addTile(int id, BufferedImage image) {
        tiles.put(id, image);
    }

    public HashMap<Integer, BufferedImage> getTiles() {
        return tiles;
    }

    public int getTilewidth() {
        return tilewidth;
    }
}
