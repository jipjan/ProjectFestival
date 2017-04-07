package Mapviewer.Tiled;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thijs on 20-2-2017.
 */
public class TileMap {
    private ArrayList<TileLayer> _tileLayers = new ArrayList<>();


    private ArrayList<Tileset> tilesets = new ArrayList<>();
    private ArrayList<TileLayer> layers = new ArrayList();
    private ArrayList<BufferedImage> tiles = new ArrayList();


    public ObjectLayer layerobjects;

    private int width;
    private int height;
    private int nextObjectId;
    private String orientation;
    private int tileWidth;
    private int tileHeight;
    private int version;
    private String renderOrder;
    private int rows;
    private int columns;

    public TileMap(String resourcePath) {
        try (JsonReader reader = Json.createReader(new FileReader(resourcePath))) {
            JsonObject o = (JsonObject) reader.read();

            // Save all data of the root object (TileMap)
            height = o.getInt("height");
            width = o.getInt("width");
            nextObjectId = o.getInt("nextobjectid");
            orientation = o.getString("orientation");
            renderOrder = o.getString("renderorder");
            tileWidth = o.getInt("tilewidth");
            tileHeight = o.getInt("tileheight");
            version = o.getInt("version");

            // Parse tilesets
            JsonArray tilesetsArray = o.getJsonArray("tilesets");

            for (int t = 0; t < tilesetsArray.size(); t++) {
                JsonObject tilesetObject = tilesetsArray.getJsonObject(t);

                Tileset tileset = new Tileset();
                tileset.setColumns(tilesetObject.getInt("columns"));
                tileset.setFirstgid(tilesetObject.getInt("firstgid"));

                // Replace Tiled path format with Java path format, replace slashes
                tileset.setImagePath(tilesetObject.getString("image").replaceAll("\\.\\./", "/"));
                tileset.setImageHeight(tilesetObject.getInt("imageheight"));
                tileset.setImageWidth(tilesetObject.getInt("imagewidth"));
                tileset.setMargin(tilesetObject.getInt("margin"));
                tileset.setName(tilesetObject.getString("name"));
                tileset.setSpacing(tilesetObject.getInt("spacing"));
                tileset.setTileCount(tilesetObject.getInt("tilecount"));
                tileset.setTileHeight(tilesetObject.getInt("tileheight"));
                tileset.setTileWidth(tilesetObject.getInt("tilewidth"));

                try {
                    tileset.setTilesetImage(ImageIO.read(this.getClass().getResourceAsStream(tileset.getImagePath())));
                } catch (Exception e) {
                    System.out.println("Er mist een image: " + tileset.getImagePath());
                }

                tilesets.add(tileset);

                // TODO: Add comments regarding what this code does
                int index = tileset.getFirstgid();
                while (this.tiles.size() < index + tileset.getTileCount()) {
                    this.tiles.add(null);
                }

                System.out.println("#" + t + " | Parse Tiles");
                // Parse tiles
                // TODO: Add comments regarding what this code does
                for (int y = 0; y + tileset.getTileHeight() <= tileset.getImageHeight(); y += tileset.getTileHeight()) {
                    for (int x = 0; x + tileset.getTileWidth() <= tileset.getImageWidth(); x += tileset.getTileWidth()) {
                        Tile tile = new Tile(tileset.getTileWidth(), tileset.getTileHeight(), tileset.getTilesetImage().getSubimage(x, y, tileset.getTileWidth(), tileset.getTileHeight()));
                        this.tiles.set(index, tile.getTileImage());
                        index++;
                    }
                }
            }

            // Parse layers
            // TODO: Add comments regarding what this code does
            JsonArray jsonLayers = o.getJsonArray("layers");
            for (int i = 0; i < jsonLayers.size(); i++) {
                Layer layer = null;

                JsonObject jsonObject = jsonLayers.getJsonObject(i);
                String type = jsonObject.getString("type");
                switch (type) {
                    case "tilelayer":
                        _tileLayers.add(new TileLayer(jsonObject, this));
                        break;
                    case "objectgroup":
                        layer = new ObjectLayer(jsonObject, this);
                        break;
                    case "imagelayer":
                        //layer = new ImageLayer(jsonObject, this);
                        break;
                }

                if (layer instanceof ObjectLayer) {
                    layerobjects = (ObjectLayer) layer;
                } else
                    System.out.println("Deze layer is aids");
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
    }




    public ArrayList<TileLayer> getTileLayers() {
        return _tileLayers;
    }

    public void draw(Graphics2D g2) {
        for (TileLayer l : this.layers) {
            g2.drawImage(l.getImage(), null, null);
        }
    }

    public BufferedImage[] getTiles() {
        return this.tiles.toArray(new BufferedImage[tiles.size()]);
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public ArrayList<TileLayer> getLayers()
    {
        return layers;
    }
}