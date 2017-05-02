package NewAI.AILogic;

import Events.*;
import GUI.CurrentSetup;
import Mapviewer.TiledMapReader.JsonClasses.ObjectLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;

import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jacco on 30/04/2017.
 */
public class AILogicRunner{
    private static final boolean debugOn = true;
    private ArrayList<TileObject> _toilets;
    private ArrayList<TileObject> _podia;
    private Events _events;
    public LocalTime _time;
    private static int _TimeMiltiplier = 100;
    private LocalTime _nextSecond;

    public AILogicRunner(ArrayList<ObjectLayer> mapObjectLayers)
    {
        ArrayList<TileObject> objects;
        objects = mapObjectLayers.get(0).getObjects();

        _toilets = new ArrayList<>();
        _podia = new ArrayList<>();

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
                        object.getName() + " type: " + object.getType() + " x: "+ object.getX()+ " y: "+ object.getY()+ '\n'
                );
            }
        }

        _events = CurrentSetup.Events;

        if (debugOn) {
            Iterator<Event> eventIterator = _events.listIterator();
            while (eventIterator.hasNext()) {
                Event event = eventIterator.next();
                System.out.println(event.getPerformer() + " - " +  event.toString());
            }
        }
        _time = LocalTime.of(0,0);
        _nextSecond = LocalTime.now();
    }

    public void updateTime()
    {
        if (_nextSecond.isBefore(LocalTime.now()) ) {
            _nextSecond = LocalTime.now().plusSeconds(1);
            _time = _time.plusSeconds(_TimeMiltiplier);
            if (debugOn)
            {
                System.out.println(_time.getSecond()+ " "+ _time.getMinute());
            }
        }
    }

    public TileObject returnRandomToilet()
    {
        return _toilets.get((int) (Math.random() *_toilets.size()));
    }

    public TileObject returnRandomPodium()
    {
        return _podia.get((int) (Math.random() *_podia.size()));
    }
}
