package Mapviewer.TiledMapReader.JsonClasses;

import HelperClasses.PairingHashMap;
import com.google.gson.JsonArray;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileLayer {
    private transient PairingHashMap data;
    private int height;
    private String name;
    private int opacity;
    private String type;
    private boolean visible;
    private int width;
    private int x, y;
    private transient BufferedImage drawnLayer;

    public void setData(JsonArray obj) {
        data = new PairingHashMap(height * width);
        int i = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                data.add(x, y, obj.get(i).getAsInt());
                i++;
            }
    }

    public void setDrawnLayer(BufferedImage img) {
        drawnLayer = img;
    }

    public BufferedImage getDrawnLayer() {
        return drawnLayer;
    }

    public PairingHashMap<Integer> getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getOpacity() {
        return opacity;
    }

    public String getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
