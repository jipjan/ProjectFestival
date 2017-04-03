package AI.mood;

import AI.GridLocation;

/**
 * Created by jacco on 23/03/2017.
 */
public interface Mood {
    int _likelihood = 0;//weight chance person switches to this mood;

    GridLocation giveDestination(); //gives a destination npc wants to go to
    long timeToSpendAfterAtDestination(); //gives number of tics to wait before updating to new mood or destiny
    Mood updateMood(); //gives new mood if needed;

    static Mood moodIterator(Mood[] moodArray , int moodIterator , int currentLikelihoodCount, int likelihoodNumberToLookFor)
    {
        if (moodIterator == moodArray.length-1 )
            return moodArray[moodIterator];

        int currentLikelihood = moodArray[moodIterator]._likelihood;
        if (likelihoodNumberToLookFor > currentLikelihoodCount &&
                likelihoodNumberToLookFor < currentLikelihoodCount + currentLikelihood)
            return moodArray[moodIterator];
        else
            return moodIterator(moodArray, moodIterator+1, currentLikelihoodCount + currentLikelihood, likelihoodNumberToLookFor);
    }
}
