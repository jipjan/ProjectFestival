package NewAI.mood;

import NewAI.pathFinding.GridLocation;
import Events.*;
/**
 * Created by jacco on 23/03/2017.
 */
public class partyMood implements IMood {
    public static int _likelyhood = 10;
    Event curentEvent;

    @Override
    public GridLocation giveDestination() {
        Events ongoingEvents = getOngoingEvents();
        int totalPopularity = 0;
        for (Event e: ongoingEvents)
        {
            totalPopularity += e.getPopularity();
        }

        double eventNumber = Math.random() * totalPopularity;
        Event eventToGoTo = null;
        for (Event e: ongoingEvents)
        {
            eventNumber -= e.getPopularity();
            if (eventNumber <= 0)
            {
                eventToGoTo = e;
                break;
            }
        }
        return getEventLocation(eventToGoTo);
    }

    @Override
    public long timeToSpendAfterAtDestination(Time time, Events events) {
        return 0;//todo
    }

    @Override
    public IMood updateMood() {
        return new moodless();
    }

    private Events getOngoingEvents()
    {
        return null;//todo
    }

    private int getEventTime(Event event)
    {
        return 0; //todo
    }

    private GridLocation getEventLocation(Event event)
    {
        return null; //todo
    }
}
