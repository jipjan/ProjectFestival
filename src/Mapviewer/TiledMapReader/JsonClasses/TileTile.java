package Mapviewer.TiledMapReader.JsonClasses;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class TileTile {
    private int id;
    private short[] terrain = new short[4];

    public TileTile(int id, short[] terrain) {
        this.id = id;
        this.terrain = terrain;
    }

}
