package Mapviewer.Mapviewer;

import Mapviewer.TiledMapReader.MyTiledJsonParser;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.Mapviewer.Drawers.Draw;
import NewAI.BaseClasses.MyNpcWorld;
import NewAI.NewPathfinding.Grid2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created by Thijs on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener {
    private static final int NPCs = 1;
    private boolean _debug, _grid = false;

    private TiledMapDrawer _map;
    private Camera _camera;
    private MyNpcWorld _world;
    private double _lastTime = 0;
    private BufferedImage _pathLayer;
    private Grid2d _pathfinder;

    public MapViewer() {
        _map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1 Test.json");
        _camera = new Camera(this, 1.0d, new Point2D.Double(_map.getWidth() / 2, _map.getHeight() / 2));
        _world = new MyNpcWorld(NPCs, _map);

        _pathfinder = new Grid2d(_map.getTileLayers().get(0), false);
        _world.testPath(_pathfinder);

        new Timer(16, this).start();
    }

    public void setHeatmap(boolean on) {
        if (on)
            Draw.startHeatmapDrawer(_world.getNpcs(), _world.getWidth(), _world.getHeight(), 250);
        else
            Draw.stopHeatmapDrawer();
    }

    public void setPathlayerVisualization(boolean on) {
        if (on)
            ;//_pathLayer = DebugDraw.drawPathLayer(_pathfinder.getPathfinderGrid(), _world.getWidth(), _world.getHeight());
        else
            _pathLayer = null;
    }

    public void setDebug(boolean on) {
        _debug = on;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long time = System.nanoTime();
        double elapsedTime = (time- _lastTime) / 1e9;
        _lastTime = time;
        _world.updateNpcs();
        _world.update(elapsedTime);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if (_grid)
            Draw.drawGrid(this, _camera, g2d, 32);

        g2d.setTransform(_camera.getTransform(getWidth(), getHeight()));
        _map.drawMap(g2d);
        _world.drawWorld(g2d, _debug);
        g2d.drawImage(Draw.getHeapmap(), null, null);
        g2d.drawImage(_pathLayer, null, null);
    }
}
