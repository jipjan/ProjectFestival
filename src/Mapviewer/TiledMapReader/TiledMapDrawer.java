package Mapviewer.TiledMapReader;

import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileMap;
import Mapviewer.TiledMapReader.JsonClasses.TileSet;

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
                layer.setDrawnLayer(drawLayer(layer, tilesets.get(0)));
            g.drawImage(layer.getDrawnLayer(), 0, 0, null);
        }
    }

    public void drawMap(Graphics2D g2) {
        if (map == null)
            redrawMap();
        g2.drawImage(map, null, null);
    }

    private BufferedImage drawLayer(TileLayer layer, TileSet tiles) {
        BufferedImage img = new BufferedImage(layer.getWidth() * tiles.getTilewidth(), layer.getHeight() * tiles.getTileheight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = img.createGraphics();
        for (int y = 0; y < layer.getHeight(); y++)
            for (int x = 0; x < layer.getWidth(); x++)
                g2.drawImage(tiles.getTile(layer.getData().getTileId(x, y)), x * tiles.getTilewidth(), y * tiles.getTileheight(), null);
        return img;
    }
}
