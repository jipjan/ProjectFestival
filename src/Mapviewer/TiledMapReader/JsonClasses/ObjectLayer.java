package Mapviewer.TiledMapReader.JsonClasses;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class ObjectLayer {
    private String draworder;
    private int height;
    private String name;
    private ArrayList<TileObject> objects;
    private int opacity;
    private String type;
    private boolean visible;
    private int width;
    private int x, y;
}
