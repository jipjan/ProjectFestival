package Mapviewer.TiledMapReader.JsonClasses;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileObject {
    private int gid, height, id;
    private String name;
    private int rotation;
    private String type;
    private boolean visible;
    private int width, x, y;

    public int getGid() {
        return gid;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRotation() {
        return rotation;
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
