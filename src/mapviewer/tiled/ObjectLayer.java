package mapviewer.tiled;

import javax.json.JsonObject;

/**
 * Created by Thijs on 20-2-2017.
 */
public class ObjectLayer extends Layer {


    public ObjectLayer(JsonObject layer, TileMap map)
    {
        super(layer, map);
    }
}
