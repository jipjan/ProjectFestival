package Mapviewer.TiledMapReader;

import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 8-4-2017.
 */
public class TiledMapDrawer extends TileMap {
    private transient BufferedImage map;

    private void redrawMap() {
        map = new BufferedImage(width * tilewidth, height * tileheight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = map.createGraphics();
        for (TileLayer layer : tilelayers) {
            if (layer.getDrawnLayer() == null)
                layer.setDrawnLayer(tilesets.get(0));
            g.drawImage(layer.getDrawnLayer(), 0, 0, null);
        }
    }

    public void drawMap(Graphics2D g2) {
        if (map == null)
            redrawMap();
        g2.drawImage(map, null, null);
    }
}
