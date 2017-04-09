package Mapviewer.TiledMapReader;

import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;

/**
 * Created by Jaap-Jan on 7-4-2017.
 */
public class MyTester {
    public static void main(String[] args) {
        TiledMapDrawer map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1.json");
        map.getTilesets().get(0).getTile(290);
        System.out.println(map);
    }
}
