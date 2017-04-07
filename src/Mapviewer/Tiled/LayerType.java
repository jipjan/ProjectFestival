package Mapviewer.Tiled;

/**
 * Created by Thijs on 20-2-2017.
 */
public enum LayerType {
    TileLayer("tilelayer"),
    ObjectLayer("objectlayer"),
    ImageLayer("imagelayer");

    private final String name;

    LayerType(String name)
    {
        this.name = name;
    }
}