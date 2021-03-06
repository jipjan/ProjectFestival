package Mapviewer.Mapviewer;

import AI.Npc;
import GUI.CurrentSetup;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

//import static GUI.CurrentSetup.NPCList;

/**
 * Created by Trist on 23-3-2017.
 */
public class ObjectStats {
    ArrayList<TileObject> objList = new ArrayList<TileObject>();
    double x;
    double y;
    String name;
    public Graphics g;
    TileObject item = new TileObject();
    public ObjectStats(ArrayList objects) {
 //       objList = objects;
//        counters();
    }


    public void counters(Graphics2D g2d) {

        for (int i = 0; i < objList.size(); i++) {
            item = objList.get(i);
            double x = item.getX();
            double y = item.getY();
            String name = item.getName();
            int aantalNPCs = getNPCs(x,y);
            g2d.drawString("Naam: "+name +" Aantal bezoekers: "+ aantalNPCs, (int) x,(int) y);
        }
    }
/* Wordt ergens anders getekend
    public void draw(Graphics g){
        double x = item.getX();
        double y = item.getY();
        String name = item.getName();
        g.drawString("Naam: "+name +" Aantal bezoekers: "+ aantalNPCs, (int) x,(int) y);
    }*/
    public int getNPCs(double x,double y){
        //Pas dit aan naar de huidige NPC lijst
        ArrayList<Npc> npcs = CurrentSetup.Npcs;
        int aantal = 0;
        for (int i =0;i<npcs.size();i++){
         Npc npc = npcs.get(i);
         Point2D locatie = npc.getLocation();
         double xnpc=locatie.getX();
         double ynpc=locatie.getY();
         if(xnpc<x && xnpc+100>x || ynpc<x && ynpc+100>y ){
             aantal++;
         }
        }
          return aantal;
    }
}


