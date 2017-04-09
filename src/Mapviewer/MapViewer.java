package Mapviewer;

import AI.NewNpcLogic;
import Mapviewer.TiledMapReader.JsonClasses.*;
import Mapviewer.TiledMapReader.MyTiledJsonParser;
import Mapviewer.TiledMapReader.TiledMapDrawer;
import NewAI.*;
import AI.pathFinding.DistanceGrid;
import Mapviewer.Mapviewer.Camera;
import Mapviewer.Mapviewer.DebugDraw;
import Mapviewer.Mapviewer.Draw;
import Mapviewer.Mapviewer.ObjectStats;
import NewAI.BaseClasses.MyBodies;
import NewAI.BaseClasses.MyBody;
import NewAI.BaseClasses.MyNpcs;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import Sprites.*;

/**
 * Created by Thijs on 20-2-2017.
 */
public class MapViewer extends JPanel implements ActionListener {
    private static final int AMOUNTOFNPCS = 50;

    private TiledMapDrawer map;
    private Camera camera;
    private double lastTime = 0;
    private World w = new World();
    private MyNpcs npcs;
    private MyBodies _myBodies;
    private static Graphics2D g2d;
    private NewNpcLogic _NpcLogic;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Map Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH | JFrame.NORMAL);
        frame.setContentPane(new MapViewer());
        frame.pack();
        frame.setVisible(true);
    }

    public MapViewer() {
        map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1 Test.json");

        this.camera = new Camera(this, 1.0d, new Point2D.Double(map.getWidth() / 2, map.getHeight() / 2));

        Sprites.Init();

        w.setGravity(new Vector2(0, 0));

        npcs = new MyNpcs(AMOUNTOFNPCS);

        for (int i = 0; i < AMOUNTOFNPCS; i++) {
            MyNpc npc = new MyNpc(50, 50);
            w.addBody(npc);
            npcs.add(npc);
        }

        _myBodies = new MyBodies();

        for(TileObject t : map.getObjectLayers().get(0).getObjects())
        {
            System.out.println(t.getName());
            TileSet s = map.getTilesets().getTileSetByGid(t.getGid());
            _myBodies.add(new MyBody(s.getTile(1), t.getX(), t.getY()));
        }

        _NpcLogic = new NewNpcLogic(npcs, map);
        new Timer(10, this).start();
    }


    private void drawGrid(Graphics2D g2d)
    {
        int stepSize = 32;

        g2d.setColor(Color.lightGray);
        int centerX = (int) ((this.getWidth() / 2) + (this.camera.getCenterPoint().getX() * this.camera.getZoom()));
        int centerY = (int) ((this.getHeight() / 2) + (this.camera.getCenterPoint().getY() * this.camera.getZoom()));

        // Draw rows
        for(int y = 0; y < this.getHeight(); y+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(0, centerY + y, this.getWidth(), centerY + y);
            g2d.drawLine(0, centerY - y, this.getWidth(), centerY - y);
        }

        // Draw columns
        for(int x = 0; x < this.getWidth(); x+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(centerX + x, 0, centerX + x, this.getHeight());
            g2d.drawLine(centerX - x, 0, centerX - x, this.getHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long time = System.nanoTime();
        double elapsedTime = (time-lastTime) / 1000000000.0;
        lastTime = time;
        w.update(elapsedTime);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        drawGrid(g2d);
        AffineTransform origin = g2d.getTransform();
        g2d.setTransform(this.camera.getTransform(getWidth(), getHeight()));
        map.drawMap(g2d);
        DebugDraw.draw(g2d, w, 1);
        Draw.draw(g2d, _myBodies, 1);
        Draw.draw(g2d, npcs, 1);
        g2d.setTransform(origin);
    }
}
