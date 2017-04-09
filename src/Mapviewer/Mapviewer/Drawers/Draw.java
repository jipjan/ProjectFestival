package Mapviewer.Mapviewer.Drawers;

import Mapviewer.Mapviewer.Camera;
import Mapviewer.Mapviewer.MapViewer;
import NewAI.BaseClasses.MyBody;
import NewAI.BaseClasses.MyNpcs;
import NewAI.MyNpc;
import org.dyn4j.collision.Fixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 15-3-2017.
 */
public class Draw {

    public static void drawHeatmap(Graphics2D g2d, MyNpcs npcs, int mapWidth, int mapHeight){
        BufferedImage img = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        float total = npcs.size();
        for (int x = 0; x < mapWidth; x += 32) {
            for (int y = 0; y < mapHeight; y += 32) {
                float amount = 0;
                for (MyNpc npc : npcs) {
                    Vector2 center = npc.getWorldCenter();
                    if (center.x > x && center.x < x + 32)
                        if (center.y > y && center.y < y + 32)
                            amount++;
                }
                Color r = new Color(1f, 0f, 0f, Math.min(amount/total * 5, 1));
                g.setPaint(r);
                g.fillRect(x, y, 32, 32);
            }
        }
        g2d.drawImage(img, null, null);
    }


    public static void drawGrid(JPanel panel, Camera camera, Graphics2D g2d, int stepSize) {
        g2d.setColor(Color.lightGray);
        int centerX = (int) ((panel.getWidth() / 2) + (camera.getCenterPoint().getX() * camera.getZoom()));
        int centerY = (int) ((panel.getHeight() / 2) + (camera.getCenterPoint().getY() * camera.getZoom()));

        // Draw rows
        for(int y = 0; y < panel.getHeight(); y+=(stepSize * camera.getZoom()))
        {
            g2d.drawLine(0, centerY + y, panel.getWidth(), centerY + y);
            g2d.drawLine(0, centerY - y, panel.getWidth(), centerY - y);
        }

        // Draw columns
        for(int x = 0; x < panel.getWidth(); x+=(stepSize * camera.getZoom()))
        {
            g2d.drawLine(centerX + x, 0, centerX + x, panel.getHeight());
            g2d.drawLine(centerX - x, 0, centerX - x, panel.getHeight());
        }
    }


    public static<T extends MyBody> void  drawSprites(Graphics2D g2d, ArrayList<T> bodies, double scale) {

        for(MyBody b : bodies)
        {
            AffineTransform originalTransform = g2d.getTransform();
            AffineTransform bodyTransform = new AffineTransform();
            bodyTransform.translate(b.getTransform().getTranslationX() * scale, b.getTransform().getTranslationY() * scale);
            bodyTransform.rotate(b.getTransform().getRotation());
            g2d.transform(bodyTransform);

            for(Fixture f : b.getFixtures()) {
                Convex s = f.getShape();

                if (s instanceof Rectangle) {
                    Rectangle r = (Rectangle) s;
                    Vector2 c = r.getCenter();
                    double w = r.getWidth();
                    double h = r.getHeight();
                    g2d.drawImage(b.Sprite,
                            (int) Math.ceil((c.x - w / 2.0) * scale),
                            (int) Math.ceil((c.y - h / 2.0) * scale),
                            (int) Math.ceil(w * scale),
                            (int) Math.ceil(h * scale),
                            null);
                } else if (s instanceof Circle) {
                    Circle c = (Circle) s;
                    double r = c.getRadius();
                    Vector2 cc = c.getCenter();
                    int x = (int)Math.ceil((cc.x - r) * scale);
                    int y = (int)Math.ceil((cc.y - r) * scale);
                    int w = (int)Math.ceil(r * 2 * scale);
                    g2d.drawImage(b.Sprite, x, y, w, w, null);
                }
            }
            g2d.setTransform(originalTransform);
        }

    }
}
