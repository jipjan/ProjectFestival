package Mapviewer.Mapviewer.Drawers;

import HelperClasses.PairHashMap;
import HelperClasses.PairingHashMap;
import Mapviewer.TiledMapReader.JsonClasses.TileLayer;
import NewAI.MyNpc;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Polygon;

import java.awt.*;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johan on 2017-03-08.
 */
public class DebugDraw {
	public static BufferedImage drawPathLayer(double[][] grid, int mapWidth, int mapHeight) {
		BufferedImage img = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		g.setPaint(new Color(0, 0, 0, 0.5f));
		for (int x = 0; x < 100; x++)
			for (int y = 0; y < 100; y++)
				if (grid[y][x] < 0)
					g.fill(new Rectangle2D.Double(x * 32, y * 32, 32, 32));
		return img;
	}

	public static void draw(Graphics2D g2d, World world, double scale) {
		for(Body b : world.getBodies())
		{
			AffineTransform originalTransform = g2d.getTransform();

			AffineTransform bodyTransform = new AffineTransform();
			bodyTransform.translate(b.getTransform().getTranslationX() * scale, b.getTransform().getTranslationY() * scale);
			bodyTransform.rotate(b.getTransform().getRotation());
			g2d.transform(bodyTransform);


			for(Fixture f : b.getFixtures())
				g2d.draw(AffineTransform.getScaleInstance(scale,scale).createTransformedShape(getShape(f.getShape(), scale)));


			g2d.setTransform(originalTransform);
		}
	}

	private static Shape getShape(Convex shape, double scale) {
		if(shape instanceof Polygon)
			return drawShape((Polygon)shape, scale);
		if(shape instanceof Circle)
			return drawShape((Circle)shape, scale);
		else
			System.out.println("Unsupported shape: " + shape);
		return null;
	}

	private static Shape drawShape(Polygon shape, double scale) {
		GeneralPath path = new GeneralPath();
		Vector2[] vertices = shape.getVertices();
		path.moveTo(vertices[0].x, vertices[0].y);
		for(int i = 1; i < vertices.length; i++)
			path.lineTo(vertices[i].x, vertices[i].y);
		path.closePath();
		return path;
	}

	private static Shape drawShape(Circle shape, double scale) {
		return new Ellipse2D.Double(shape.getCenter().x - shape.getRadius(),
																shape.getCenter().y - shape.getRadius(),
																shape.getRadius()*2,
																shape.getRadius()*2);
	}


}
