package mapviewer.mapviewer;

import mapviewer.MapViewer;
import mapviewer.tiled.Item;
import mapviewer.tiled.ObjectLayer;
import mapviewer.tiled.TileMap;
import javax.swing.*;
import javax.json.JsonObject;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Trist on 23-3-2017.
 */
public class ObjectStats {
    ArrayList<Item> objList = new ArrayList<Item>();
    double x;
    double y;
    String name;
    Graphics g;
    Item item = new Item("", 0, 0);
    public ObjectStats(ArrayList objects) {
        objList = objects;
        counters();
    }

    public void counters() {

        for (int i = 0; i < objList.size(); i++) {
            item = objList.get(i);
            double x = item.getX();
            double y = item.getY();
            String name = item.getName();
            draw(g);
        }
    }

    public void draw(Graphics g){
        double x = item.getX();
        double y = item.getY();
        String name = item.getName();
        g.drawString(name, (int) x,(int) y);
    }
    
    //to do: Updateable maken, NPCs locatie uitlezen en betekenis geven.
}


