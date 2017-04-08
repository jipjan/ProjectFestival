package Mapviewer.TiledMapReader.JsonClasses;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileMap {
    private int height;
    private ArrayList<TileLayer> tilelayers = new ArrayList<>();
    private ArrayList<ObjectLayer> objectlayers= new ArrayList<>();
    private int nextobjectid;
    private String orientation;
    private String renderorder;
    private int tileheight;
    private ArrayList<TileSet> tilesets = new ArrayList<>();
    private int tilewidth;
    private int version;
    private int width;

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

    public ArrayList<TileSet> getTilesets() {
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

    public void drawLayers(Graphics2D g2) {
        for (TileLayer layer : tilelayers)
            g2.drawImage(layer.getDrawnLayer(), null, null);
    }
}
