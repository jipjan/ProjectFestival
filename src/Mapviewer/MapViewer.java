package Mapviewer;

import AI.NewNpcLogic;
import Mapviewer.TiledMapReader.JsonClasses.*;
import Mapviewer.TiledMapReader.LayerDrawer;
import Mapviewer.TiledMapReader.MyTiledJsonParser;
import Mapviewer.TiledMapReader.TiledMapDrawer;
import NewAI.*;
import AI.mood.moodless;
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
    private ObjectLayer objectLayer; //geen idee of dit goed is
    private int linesH, linesV;
    private ArrayList<Point2D> _startLocations;
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

    Random r = new Random();

    public MapViewer() {
        map = MyTiledJsonParser.jsonToTileMap("./resources/Festivalplanner Map V1 Test.json");

        this.camera = new Camera(this, 1.0d, new Point2D.Double(map.getWidth() / 2, map.getHeight() / 2));

        Sprites.Init();

        _startLocations = new ArrayList<>();
        _startLocations.add(new Point2D.Double(0, 300));

        w.setGravity(new Vector2(0, 0));

        npcs = new MyNpcs(AMOUNTOFNPCS);


        DistanceGrid testDestination = new DistanceGrid((int) map.getObjectLayers().get(0).getX()/32, (int) map.getObjectLayers().get(0).getY()/32,map);

        for (int i = 0; i < AMOUNTOFNPCS; i++) {
            Point2D startLoc = _startLocations.get(r.nextInt(_startLocations.size()));

            MyNpc npc = new MyNpc(50 + startLoc.getX() + npcs.size()*25, startLoc.getY() + 500);
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

    private void drawStats(Graphics2D g2d)
    {
        if (map.getObjectLayers().get(0)==null) return;
        ObjectStats objstats = new ObjectStats(map.getObjectLayers().get(0).getObjects());
        objstats.counters(g2d);
        /*
        int statCount = 12;
        int statHeight = 14;

        g2d.setColor(Color.red);
        g2d.fillOval((this.getWidth() / 2) + (int)(this.camera.getCenterPoint().getX() * this.camera.getZoom()) - 5, (this.getHeight() / 2) + (int)(this.camera.getCenterPoint().getY() * this.camera.getZoom()) - 5, 10,10);
        g2d.setColor(Color.blue);
        g2d.fillOval(this.getWidth() / 2 - 5, this.getHeight() / 2 - 5, 10,10);
        g2d.setColor(Color.gray);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.fillRect(0, 0, 350, statCount * statHeight);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.setColor(Color.white);
        g2d.translate(0, statHeight);
        g2d.drawString("Zoom Factor: " + this.camera.getZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Max Zoom:" + this.camera.getMaxZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Min Zoom:" + this.camera.getMinZoom(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Origin Point: X: " + (int)this.camera.getCenterPoint().getX() + "|Y: " + (int)this.camera.getCenterPoint().getY(), 0,0);
        g2d.translate(0, statHeight);
        g2d.drawString("World Position: ", 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Mouse Point: X:" + (int)MouseInfo.getPointerInfo().getLocation().getX() + "|Y: " + (int)MouseInfo.getPointerInfo().getLocation().getY(), 0,0);
        g2d.translate(0, statHeight);
        g2d.drawString("Grid Lines Vertical:" + this.linesV, 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Grid Lines Horizontal:" + this.linesH, 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Width:" + this.map.getWidth(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Map Height:" + this.map.getHeight(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Tile Width:" + this.map.getTileWidth(), 0, 0);
        g2d.translate(0, statHeight);
        g2d.drawString("Tile Height:" + this.map.getTileHeight(), 0, 0);
        g2d.translate(0, -(statCount * statHeight));
        */
    }

    private void drawGrid(Graphics2D g2d)
    {
        int stepSize = 32;

        int lV = 0;
        int lH = 0;

        g2d.setColor(Color.lightGray);
        int centerX = (int) ((this.getWidth() / 2) + (this.camera.getCenterPoint().getX() * this.camera.getZoom()));
        int centerY = (int) ((this.getHeight() / 2) + (this.camera.getCenterPoint().getY() * this.camera.getZoom()));

        // Draw rows
        for(int y = 0; y < this.getHeight(); y+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(0, centerY + y, this.getWidth(), centerY + y);
            g2d.drawLine(0, centerY - y, this.getWidth(), centerY - y);
            lH+=2;
        }

        // Draw columns
        for(int x = 0; x < this.getWidth(); x+=(stepSize * this.camera.getZoom()))
        {
            g2d.drawLine(centerX + x, 0, centerX + x, this.getHeight());
            g2d.drawLine(centerX - x, 0, centerX - x, this.getHeight());
            lV+=2;
        }

        this.linesH = lH;
        this.linesV = lV;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (MyNpc c : npcs) {
//            c.setDestination(map.layerobjects.getObjectList().get(1).getX(), map.layerobjects.getObjectList().get(1).getY());
            //c.update(map);
            TileObject i = map.getObjectLayers().get(0).getObjects().get(r.nextInt(map.getObjectLayers().get(0).getObjects().size()));
        }
        long time = System.nanoTime();
        double elapsedTime = (time-lastTime) / 1000000000.0;
        lastTime = time;
        w.update(elapsedTime);
        _NpcLogic.update();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        this.drawGrid(g2d);

        // KEEP THIS ORDER - DO NOT EDIT UNLESS FUCKERY IS WANTED
        g2d.setTransform(this.camera.getTransform(getWidth(), getHeight()));

        // Camera transfom is needed for the map to draw and to keep correct ratio's
        //this.map.draw(g2d);
        //for (TileLayer layer : map.getTileLayers())
        //    LayerDrawer.drawLayer(g2d, layer, map.getTilesets().get(0));
        map.drawMap(g2d);

        // Reset camera transform
        g2d.scale(1 / this.camera.getZoom() , 1 / this.camera.getZoom());
        g2d.translate(-(this.getWidth() / 2) - this.camera.getCenterPoint().getX() * this.camera.getZoom(), -(this.getHeight() / 2) - this.camera.getCenterPoint().getY() * this.camera.getZoom());
        // Done resetting camera transform
        // YOU CAN EDIT BEYOND THIS POINT AGAIN!

        // this.drawStats(g2d);

        // Draw NPCs
        AffineTransform originalTransform = g2d.getTransform();
        g2d.setTransform(camera.getTransform(getWidth(), getHeight()));
        DebugDraw.draw(g2d, w, 1);
        Draw.draw(g2d, _myBodies, 1);
        Draw.draw(g2d, npcs, 1);
        g2d.setTransform(originalTransform);
    }
}
