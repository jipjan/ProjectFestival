package AI;

import java.util.*;


public class Npcs extends Hashtable<Long, Npc> {
    long _id = 0;

    public Npc createNpc() {
     //   Npc n = new Npc();
      //  put(_id, n);
      //  _id++;
        return new Npc(null);
    }

    public void createNpcs(int amount) {
        for (int i = 0; i < amount; i++)
            createNpc();
    }

    public void removeNpc(long _id) {
        remove(_id);
    }

    public Npc getNpcById(long id) {
        return get(id);
    }

    public ArrayList<Npc> getNpcsByMood(Mood m) {
        ArrayList<Npc> toReturn = new ArrayList<Npc>();
        for (Iterator<Map.Entry<Long, Npc>> it = this.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, Npc> i = it.next();
            if (i.getValue().getMood() == m) {
                toReturn.add(i.getValue());
            }
        }
        return toReturn;
    }


    //private ArrayList<Npc> getNpcs()
}
