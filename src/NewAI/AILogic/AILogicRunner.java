package NewAI.AILogic;

import Events.*;
import GUI.CurrentSetup;
import Mapviewer.TiledMapReader.JsonClasses.ObjectLayer;
import Mapviewer.TiledMapReader.JsonClasses.TileObject;
import de.jaret.util.date.JaretDate;

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
    private static int _TimeMiltiplier = 100;// miltiplier per second
    private LocalTime _nextSecond;
    Events _currentOngoingEvents = new Events();

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

            for (Event event: _events)//todo fix for when events end goes past or events Begin goes before 12 O'clock
            {
                LocalTime eventBeginTime = jaredDateToLocalTime(event.getTime().getBeginDate());
                LocalTime eventEndTime = jaredDateToLocalTime(event.getTime().getEndDate());
                if (_time.isAfter(eventBeginTime)&& _time.isBefore(eventEndTime))
                {
                    _currentOngoingEvents.add(event);
                }
            }

            if (debugOn)
            {
                System.out.println(_time.getSecond()+ " "+ _time.getMinute());
            }
        }
    }


    /**
     * @return a tile object with a current event going on. if there is no event atm than returns null
     */
    public TileObject giveActualEventDestination()
    {

        ArrayList<TileObject> viableEvents= new ArrayList<>();

        return viableEvents.get(0);//todo randomize based on likelyhood percent
    }


    /** convert JaretDate to localTime loses date and less than minutes precision
     * @param jaretDate the jareddate to convert to Local time
     * @return a localTime
     */
    public static LocalTime jaredDateToLocalTime(JaretDate jaretDate)
    {
        LocalTime localTime = LocalTime.of(jaretDate.getHours(), jaretDate.getMinutes());
        return localTime;
    }

    public TileObject returnRandomToilet()
    {
        return _toilets.get((int) (Math.random() *_toilets.size()));
    }

    public TileObject returnRandomPodium()
    {
        return _podia.get((int) (Math.random() *_podia.size()));
    }

    public ArrayList<TileObject> get_podia() {
        return _podia;
    }
}
