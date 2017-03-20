package TileLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;

/*
 * Created by Thijs on 20-Feb-17.
 */
public class TiledMap
{
    private ArrayList<TiledLayer>layers = new ArrayList<>();
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    //private ArrayList<TiledMap>tilemaps = new ArrayList<>();

    public static void main(String[] args)
    {
            TiledMap tilemap = new TiledMap();
    }

    public TiledMap()
    {
        JsonObject jsonObject = null;


        JsonReader reader = null;
        try
        {
<<<<<<< HEAD:outdated/TileLoader/TiledMap.java
            reader = Json.createReader(new FileReader("MappieV2.json"));
=======
            reader = Json.createReader(new FileReader(String.valueOf(getClass().getResource("./MappieV2.json"))));
>>>>>>> origin/Bezoekerplaatje:src/TileLoader/TiledMap.java
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        JsonObject rootObject = (JsonObject)reader.read();




    }

    public void draw(Graphics2D g2d)
    {
        //hier moeten layers getekend worden
        for (TiledLayer l : this.layers)
        {
            g2d.drawImage(l.image, new AffineTransform(), null);
        }
    }
}
