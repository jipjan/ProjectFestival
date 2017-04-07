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
    private ArrayList<TileTerrain> terrains;
    private int tilecount, tileheight;
    private ArrayList<TileTile> tiles;
    private int tilewidth;
}
