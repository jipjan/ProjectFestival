package Mapviewer.TiledMapReader.JsonClasses;

import java.util.ArrayList;

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
    private transient ArrayList<TileTile> tiles = new ArrayList<>();
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

    public ArrayList<TileTile> getTiles() {
        return tiles;
    }

    public int getTilewidth() {
        return tilewidth;
    }
}
