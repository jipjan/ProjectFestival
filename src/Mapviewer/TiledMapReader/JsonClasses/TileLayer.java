package Mapviewer.TiledMapReader.JsonClasses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileLayer {
    private transient int[][] data;
    private int height;
    private String name;
    private int opacity;
    private String type;
    private boolean visible;
    private int width;
    private int x, y;

    public void setData(JsonArray obj){
        data = new int[height][width];
        int i = 0;
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                data[y][x] = obj.get(i).getAsInt();
                i++;
            }
        }
    }

    public int[][] getData() {
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
