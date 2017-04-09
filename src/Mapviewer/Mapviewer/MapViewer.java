package Mapviewer.Mapviewer;

import Mapviewer.TiledMapReader.MyTiledJsonParser;
import Mapviewer.Mapviewer.Drawers.TiledMapDrawer;
import Mapviewer.Mapviewer.Drawers.Draw;
import NewAI.BaseClasses.MyNpcWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Thijs on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener {
    private static final int NPCs = 5000;

    private TiledMapDrawer _map;
    private Camera _camera;
    private MyNpcWorld _world;
    private double lastTime = 0;

    public MapViewer() {
        _map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1 Test.json");
        _camera = new Camera(this, 1.0d, new Point2D.Double(_map.getWidth() / 2, _map.getHeight() / 2));
        _world = new MyNpcWorld(NPCs, _map);

        new Timer(15, this).start();
//        new Thread(() -> _world.updateNpcs()).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long time = System.nanoTime();
        double elapsedTime = (time-lastTime) / 1e9;
        lastTime = time;
        _world.update(elapsedTime);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Draw.drawGrid(this, _camera, g2d, 32);
        AffineTransform origin = g2d.getTransform();
        g2d.setTransform(this._camera.getTransform(getWidth(), getHeight()));
        _map.drawMap(g2d);
        _world.drawWorld(g2d);
        g2d.setTransform(origin);
    }
}
