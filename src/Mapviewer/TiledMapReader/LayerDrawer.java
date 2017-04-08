package Mapviewer.TiledMapReader;

import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class LayerDrawer {

    public static BufferedImage drawLayer(TileLayer layer, TileSet tiles) {
        BufferedImage img = new BufferedImage(layer.getWidth() * tiles.getTilewidth(), layer.getHeight() * tiles.getTileheight(), BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2 = img.createGraphics();
        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                System.out.println(layer.getData().getTileId(x, y));
                //g2.drawImage()
                //g2.drawImage(this.map.getTiles()[this.data[y][x]], x * this.tileWidth, y * this.tileHeight, null);
                //g2.drawImage(tiles.getTiles().get(layer.getData()[y][x]), x * tiles.getTilewidth(), y * tiles.getTileheight(), null);
            }
        }
        return img;
    }
}
