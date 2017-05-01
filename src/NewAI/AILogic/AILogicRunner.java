package NewAI.AILogic;

import Events.*;
import GUI.CurrentSetup;
import Mapviewer.TiledMapReader.JsonClasses.ObjectLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jacco on 30/04/2017.
 */
public class AILogicRunner implements EventChangeActionListener{
    private static final boolean debugOn = true;
    private ArrayList<TileObject> _toilets;
    private ArrayList<TileObject> _podia;
    private Events _events;

    public AILogicRunner(ArrayList<ObjectLayer> mapObjectLayers)
    {
        ArrayList<TileObject> objects;
        objects = mapObjectLayers.get(0).getObjects();

        Iterator<TileObject> objectIterator = objects.listIterator();
        while (objectIterator.hasNext()) {
            TileObject object = objectIterator.next();
            String objectName = object.getName();

            if(objectName.contains("Toilet")) {
                if (object.isVisible()) {
                    _toilets.add(object);
                }
            } else if (objectName.contains("Podium")) {
                if (object.isVisible()) {
                    _podia.add(object);
                }
            }
            if (debugOn) {
                System.out.print(
                        object.getName() + " type: " + object.getType() + '\n'
                );
            }
        }

        _events = CurrentSetup.Events;

        if (debugOn) {
            Iterator<Event> eventIterator = _events.listIterator();
            while (eventIterator.hasNext()) {
                Event event = eventIterator.next();
                Sys
            }
        }
    }

    @Override
    public void eventChange(Events events) {
        _events = events;
    }
}
