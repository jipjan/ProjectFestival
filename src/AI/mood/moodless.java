package AI.mood;

import AI.GridLocation;

/**
 * Created by jacco on 01/04/2017.
 */
public class moodless implements Mood {
    @Override
    public GridLocation giveDestination() {
        return null;
    }

    @Override
    public long timeToSpendAfterAtDestination() {
        return 0;
    }

    @Override
    public Mood updateMood()//gives new mood if needed otherwise gives itself back
    {
        final Mood[] moodArray = {new partyMood()};//all moods listed here

        //code will do the rest.
        int totalLikelihood = 0;
        for (int i = 0; i< moodArray.length; i++)
            totalLikelihood += moodArray[i]._likelihood;

        return Mood.moodIterator(moodArray, 0, 0, (int) Math.round(Math.random() * totalLikelihood));
    }
}
