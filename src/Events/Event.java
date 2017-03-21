package Events;
import de.jaret.util.date.Interval;
import de.jaret.util.date.IntervalImpl;

import java.io.Serializable;

public class Event extends IntervalImpl implements GUI.MyPanel.ITableObject, Serializable, Interval {
    short _popularity;
    String _performer;
    String _name;
    int _podium;
    Time _time;

    public Event(String name, String performer, short popularity, Time time, int podium) {
        super(time.getBeginDate(), time.getEndDate());
        _name = name;
        _popularity = popularity;
        _performer = performer;
        _time = time;
        _podium = podium;
    }

    public short getPopularity() {
        return _popularity;
    }

    public void setPopularity(short popularity) {
        _popularity = popularity;
    }

    @Override
    public long getDuration() {
        return _time.getDurationInMinutes();
    }

    public void setDuration(long duration) {
        setTime(new Time(_time.getBeginDate(), _time.getBeginDate().advanceMinutes(duration)));
    }

    public String getPerformer() {
        return _performer;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Time getTime() {
        return _time;
    }

    public void setTime(Time time) {
        _time = time;
    }

    public int getPodium() {return _podium;}

    public int getSeconds() {
        return (int) (_time.getDurationInMinutes() * 60);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return (getPopularity() == other.getPopularity()
                    && getPerformer().equals(other.getPerformer())
                    && getName().equals(other.getName())
                    && getTime().equals(other.getTime())
            );
        }
        return false;
    }

    @Override
    public String toString() {
        return _name + ": " + _begin.toDisplayStringTime() + " - " + _end.toDisplayStringTime();
    }
}
