package Mapviewer.TiledMapReader.JsonClasses;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    private TileSets tilesets = new TileSets();
    private int tilewidth;
    private int version;
    private int width;
    private transient BufferedImage map;

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

    private void redrawLayers() {
        map = new BufferedImage(width * tilewidth, height * tileheight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = map.createGraphics();
        for (TileLayer layer : tilelayers)
            g.drawImage(layer.getDrawnLayer(), 0, 0, null);
    }

    public void drawMap(Graphics2D g2) {
        if (map == null)
            redrawLayers();
        g2.drawImage(map, null, null);
    }
}
