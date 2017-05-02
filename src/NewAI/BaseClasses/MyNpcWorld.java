package NewAI.BaseClasses;

import Mapviewer.Mapviewer.Drawers.DebugDraw;
import Mapviewer.Mapviewer.Drawers.Draw;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.TiledMapReader.JsonClasses.ObjectLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;
import NewAI.MyNpc;
import NewAI.MyOneTilePathfinding;
import NewAI.NewPathfinding.Grid2d;
import NewAI.NewPathfinding.Node;
import Sprites.Sprites;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.dynamics.*;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class MyNpcWorld extends World {
    private volatile MyNpcs _npcs;
    private MyBodies _myBodies = new MyBodies();
    private int _width, _height;
    private Grid2d _pathfinder;

    public MyNpcWorld(int npcs, TiledMapDrawer map) {
        _width = map.getWidth() * map.getTilewidth();
        _height = map.getHeight() * map.getTileheight();
        _pathfinder = new Grid2d(map.getTileLayers().get(0), true);

        setGravity(new Vector2(0, 0));
        Sprites.Init();
        setNpcs(npcs);
        setObjects(map);
    }

    public MyNpcs getNpcs() {
        return _npcs;
    }

    private void setNpcs(int count) {
        _npcs = new MyNpcs(count);
        for (int i = 0; i < count; i++) {
            MyNpc npc = new MyNpc(50 + (i % 50) * 10, 250 + (i / 50) * 10, _pathfinder);
            addBody(npc);
            _npcs.add(npc);
        }
    }

    private void setObjects(TiledMapDrawer map) {
        for (ObjectLayer l : map.getObjectLayers())
            for (TileObject t : l.getObjects())
                _myBodies.add(new MyBody(map.getTilesets().getTileSetByGid(t.getGid()).getTile(1), t.getX(), t.getY()));
    }

    public void drawWorld(Graphics2D g2d, boolean debug) {
        if (debug)
            DebugDraw.draw(g2d, this, 1);
        Draw.drawSprites(g2d, _myBodies, 1);
        Draw.drawSprites(g2d, _npcs, 1);
    }

    public void updateNpcs() {
        for (MyNpc npc : _npcs)
            npc.update();
    }

    public double[][] getPathfinderGrid() {
        return _pathfinder.getPathfinderGrid();
    }

    public int getWidth() { return _width; }
    public int getHeight() { return _height; }
}
