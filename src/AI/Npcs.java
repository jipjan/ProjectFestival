package AI;

import java.util.*;

import NewAI.mood.*;
import NewAI.pathFinding.DistanceGrid;
import NewAI.pathFinding.GridLocation;

public class Npcs extends LinkedList<Npc>{
    boolean[][] _validWalkLocations;//2dMap[y][x]
    int _validWalkLocationsCount;

    Npcs()
    {
        _validWalkLocations = validWalkLocations();
        _validWalkLocationsCount = 0;
        for (int y = 0; y < _validWalkLocations.length; y++) {
            for (int x = 0; x < _validWalkLocations[y].length; x++) {
                if (_validWalkLocations[y][x])
                    _validWalkLocationsCount++;
            }
        }
    }

    public Npc createNpc() {

        return new Npc(getRandomValidWalkLocation(), new moodless());
    }

    public void createNpcs(int amount) {
        for (int i = 0; i < amount; i++)
            add(createNpc());
    }

    public void removeNpc(Npc npc) {
        remove(npc);
    }

    public ArrayList<Npc> getNpcsByMood(IMood m) {
        ArrayList<Npc> toReturn = new ArrayList<>();

        for (Iterator<Npc> it = this.listIterator(); it.hasNext(); ) {
            Npc i = it.next();
            if (i.getMood() == m) {
                toReturn.add(i);
            }
        }
        return toReturn;
    }

    private GridLocation getRandomValidWalkLocation()
    {
        int locationNr = (int) (Math.random() * _validWalkLocationsCount);
        for (int y = 0; y < _validWalkLocations.length; y++) {
            for (int x = 0; x < _validWalkLocations[y].length; x++) {
                if (_validWalkLocations[y][x])
                    locationNr--;
                if (locationNr >= 0)
                    return new GridLocation(x, y);
            }
        }
        return new GridLocation(-1, -1);
    }

    private boolean[][] validWalkLocations()
    {
        //todo
        return null;//new DistanceGrid(-1,-1, null).testArray;
    }
}
