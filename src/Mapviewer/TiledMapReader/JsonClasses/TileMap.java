package Mapviewer.TiledMapReader.JsonClasses;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileMap {
    protected int height;
    protected ArrayList<TileLayer> tilelayers = new ArrayList<>();
    protected ArrayList<ObjectLayer> objectlayers= new ArrayList<>();
    protected int nextobjectid;
    protected String orientation;
    protected String renderorder;
    protected int tileheight;
    protected TileSets tilesets = new TileSets();
    protected int tilewidth;
    protected int version;
    protected int width;

    public int getHeight() {
        return height;
    }

    public ArrayList<TileLayer> getTileLayers() {
        return tilelayers;
    }

    public ArrayList<ObjectLayer> getObjectLayers() {
        return objectlayers;
    }

    public int getNextobjectid() {
        return nextobjectid;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getRenderorder() {
        return renderorder;
    }

    public int getTileheight() {
        return tileheight;
    }

    public TileSets getTilesets() {
        return tilesets;
    }

    public int getTilewidth() {
        return tilewidth;
    }

    public int getVersion() {
        return version;
    }

    public int getWidth() {
        return width;
    }
}
