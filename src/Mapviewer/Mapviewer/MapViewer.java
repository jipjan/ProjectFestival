package Mapviewer.Mapviewer;

import Mapviewer.TiledMapReader.MyTiledJsonParser;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.Mapviewer.Drawers.Draw;
import NewAI.BaseClasses.MyNpcWorld;

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
    private static final int NPCs = 2000;
    private static final boolean DEBUG = false;
    private static final boolean GRID = false;
    private static final boolean HEATMAP = true;

    private TiledMapDrawer _map;
    private Camera _camera;
    private MyNpcWorld _world;
    private double _lastTime = 0;

    public MapViewer() {
        _map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1 Test.json");
        _camera = new Camera(this, 1.0d, new Point2D.Double(_map.getWidth() / 2, _map.getHeight() / 2));
        _world = new MyNpcWorld(NPCs, _map);

        new Timer(16, this).start();
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
        
        if (GRID)
            Draw.drawGrid(this, _camera, g2d, 32);

        g2d.setTransform(_camera.getTransform(getWidth(), getHeight()));
        _map.drawMap(g2d);
        _world.drawWorld(g2d, DEBUG);

        if (HEATMAP)
            Draw.drawHeatmap(g2d, _world.getNpcs(), _world.getWidth(), _world.getHeight(), 0.004f);
    }
}
