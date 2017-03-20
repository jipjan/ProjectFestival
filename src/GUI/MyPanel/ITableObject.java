package GUI.MyPanel;

import Events.Time;

public interface ITableObject {
    String getName();
    short getPopularity();
    long getDuration();
    Time getTime();
    int getPodium();
}
