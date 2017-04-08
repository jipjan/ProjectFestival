package Mapviewer.TiledMapReader;


import Mapviewer.TiledMapReader.JsonClasses.ObjectLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class MyTiledJsonParser {
    public TileMap Map;

    public MyTiledJsonParser(String file) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(file)));

            Gson g = new Gson();
            Map = g.fromJson(json, TileMap.class);

            //reader = new InputStreamReader(new FileInputStream(file));
            JsonObject obj = g.fromJson(json, JsonElement.class).getAsJsonObject();
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
            int x, y;
            for (Map.Entry<String, JsonElement> tile : tiles.entrySet()) {
                /*
                Short[] terrain = new Short[4];
                int counter = 0;
                for (JsonElement tItem : tile.getValue().getAsJsonObject().get("terrain").getAsJsonArray()) {
                    terrain[counter] = tItem.getAsShort();
                    counter++;
                }
                Map.getTilesets().get(0).getTiles().put(Integer.parseInt(tile.getKey()), terrain);
                */
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
