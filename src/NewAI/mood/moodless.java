package NewAI.mood;

import Events.Events;
import NewAI.pathFinding.GridLocation;
import Events.Time;

/**
 * Created by jacco on 01/04/2017.
 */
public class moodless implements IMood {
    @Override
    public GridLocation giveDestination() {
        return null;
    }

    @Override
    public long timeToSpendAfterAtDestination(Time time, Events events) {
        return 0;
    }

    @Override
    public IMood updateMood()//gives new mood if needed otherwise gives itself back
    {
        final IMood[] IMoodArray = {new partyMood()};//all moods listed here

        //code will do the rest.
        int totalLikelihood = 0;
        for (int i = 0; i< IMoodArray.length; i++)
            totalLikelihood += IMoodArray[i]._likelihood;

        return moodIterator(IMoodArray, 0, 0, (int) Math.round(Math.random() * totalLikelihood));
    }

    static IMood moodIterator(IMood[] IMoodArray, int moodIterator , int currentLikelihoodCount, int likelihoodNumberToLookFor)
    {
        if (moodIterator == IMoodArray.length-1 )
            return IMoodArray[moodIterator];

        int currentLikelihood = IMoodArray[moodIterator]._likelihood;
        if (likelihoodNumberToLookFor > currentLikelihoodCount &&
                likelihoodNumberToLookFor < currentLikelihoodCount + currentLikelihood)
            return IMoodArray[moodIterator];
        else
            return moodIterator(IMoodArray, moodIterator+1, currentLikelihoodCount + currentLikelihood, likelihoodNumberToLookFor);
    }
}
