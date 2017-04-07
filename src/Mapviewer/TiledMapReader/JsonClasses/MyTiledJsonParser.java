package Mapviewer.TiledMapReader.JsonClasses;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.json.Json;
import java.io.*;
import java.util.Map;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class MyTiledJsonParser {
    public TileMap Map;

    public MyTiledJsonParser(String file) {
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            Gson g = new Gson();
            Map = g.fromJson(reader, TileMap.class);
            reader.close();


            reader = new InputStreamReader(new FileInputStream(file));
            JsonObject obj = g.fromJson(reader, JsonElement.class).getAsJsonObject();
            JsonArray array = obj.get("layers").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject layer = array.get(i).getAsJsonObject();
                switch (array.get(i).getAsJsonObject().get("type").getAsString()) {
                    case "tilelayer":
                        TileLayer tLayer = g.fromJson(layer, TileLayer.class);
                        tLayer.setData(layer.get("data").getAsJsonArray());
                        Map.getTileLayers().add(tLayer);
                        break;
                    case "objectgroup":
                        Map.getObjectLayers().add(g.fromJson(layer, ObjectLayer.class));
                        break;
                }
            }

            array = obj.get("tilesets").getAsJsonArray();
            JsonObject tiles = array.get(0).getAsJsonObject().get("tiles").getAsJsonObject();
            for (Map.Entry<String, JsonElement> tile : tiles.entrySet()) {
                short[] terrain = new short[4];
                int counter = 0;
                for (JsonElement tItem : tile.getValue().getAsJsonObject().get("terrain").getAsJsonArray()) {
                    terrain[counter] = tItem.getAsShort();
                    counter++;
                }
                Map.getTilesets().get(0).getTiles().add(new TileTile(Integer.parseInt(tile.getKey()), terrain));
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
