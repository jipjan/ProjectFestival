package AI;

import java.sql.Time;

/**
 * Created by jacco on 23/03/2017.
 */
public class NpcLogic {
    long _MinutesFromStart;
    Npcs _currentNpcs;

    NpcLogic()
    {
        _MinutesFromStart = 0;
        _currentNpcs = new Npcs();
        _currentNpcs.createNpcs(100);
    }

    public void updateNpc()
    {
        for (Npc npc: _currentNpcs)
        {

        }
    }
}
