package AI.mood;

import AI.GridLocation;

/**
 * Created by jacco on 23/03/2017.
 */
public class partyMood implements Mood{
    public static int _likelyhood = 10;

    @Override
    public GridLocation giveDestination() {
        return null;
    }

    @Override
    public long timeToSpendAfterAtDestination() {
        return 0;
    }

    @Override
    public Mood updateMood() {
        return null;
    }

}
