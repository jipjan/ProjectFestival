package Mapviewer.Mapviewer;

import GUI.CurrentSetup;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.Mapviewer.Drawers.Draw;
import Mapviewer.Mapviewer.Drawers.DebugDraw;
import NewAI.AILogic.AILogicRunner;
import NewAI.BaseClasses.MyCollisionListener;
import NewAI.BaseClasses.MyNpcWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

/**
 * Created by Thijs on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener {
    private static final int NPCs = CurrentSetup.npcCount;
    private boolean _debug, _grid = false;
    private boolean _running = false;

    private TiledMapDrawer _map;
    private Camera _camera;
    private MyNpcWorld _world;
    private double _lastTime = 0;
    private BufferedImage _pathLayer;
    private MyCollisionListener _collision = new MyCollisionListener();

    public MapViewer() {
        _map = CurrentSetup.map;
        _camera = new Camera(this, 1.0d, new Point2D.Double(_map.getWidth() / 2, _map.getHeight() / 2));
        _world = new MyNpcWorld(NPCs, _map);
        CurrentSetup.world = _world;
        new Timer(16, this).start();
    }

    public void setCollision(boolean on) {
        if (on)
            _world.removeListener(_collision);
        else
            _world.addListener(_collision);
    }

    public void setHeatmap(boolean on) {
        if (on)
            Draw.startHeatmapDrawer(_world.getNpcs(), _world.getWidth(), _world.getHeight(), 250);
        else
            Draw.stopHeatmapDrawer();
    }

    public void setPathlayerVisualization(boolean on) {
        if (on)
            _pathLayer = DebugDraw.drawPathLayer(_world.getPathfinderGrid(), _world.getWidth(), _world.getHeight());
        else
            _pathLayer = null;
    }

    public void setDebug(boolean on) {
        _debug = on;
    }
    public void setRunning(boolean on) { _running = on;  }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!_running) return;
        long time = System.nanoTime();
        double elapsedTime = (time- _lastTime) ;
        _lastTime = time;
        CurrentSetup.aiLogicRunner.updateTime();
        _world.updateNpcs();
        _world.update(elapsedTime/ 1e9);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if (_grid)
            Draw.drawGrid(this, _camera, g2d, 32);



        AffineTransform old = g2d.getTransform();

        g2d.setTransform(_camera.getTransform(getWidth(), getHeight()));

        _map.drawMap(g2d);
        _world.drawWorld(g2d, _debug);

        g2d.drawImage(Draw.getHeapmap(), null, null);
        g2d.drawImage(_pathLayer, null, null);

        g2d.setTransform(old);
        g2d.setFont(g2d.getFont().deriveFont(32f));
        g2d.setColor(Color.red);
        g2d.drawString(CurrentSetup.aiLogicRunner._time.toString(), 50, 50);
    }
}
