package Mapviewer.Tiled;

import javax.json.JsonObject;
import java.util.ArrayList;

/**
 * Created by Tristen on 16-3-2017.
 * ObjectArrayCreator
 */
public class ObjectLayer extends Layer {
    ArrayList<Item> objList = new ArrayList<Item>();

    public ObjectLayer(JsonObject layer, TileMap map) {
        super(layer, map);

        for (int i = 0; i < layer.getJsonArray("objects").size(); i++) {
            JsonObject o = layer.getJsonArray("objects").getJsonObject(i);
            String name = o.getString("name");
            double x = o.getJsonNumber("x").doubleValue();
            double y = o.getJsonNumber("y").doubleValue();
            Item object = new Item(name, x, y);
            objList.add(object);
        }


    }

    public ArrayList<Item> getObjectList(){
        return objList;
    }
}
