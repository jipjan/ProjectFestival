package Events;
import java.io.Serializable;

public class Event implements GUI.MyPanel.ITableObject, Serializable {
    short _popularity;
    String _performer;
    String _name;
    int _podium;
    Time _time;

    public Event(String name, String performer, short popularity, Time time, int podium) {
        _name = name;
        _popularity = popularity;
        _performer = performer;
        _time = time;
        _podium = podium;
    }

    public short getPopularity() {
        return _popularity;
    }

    @Override
    public long getDuration() {
        return _time.getDurationInMinutes();
    }

    public String getPerformer() {
        return _performer;
    }

    public String getName() {
        return _name;
    }

    public Time getTime() {
        return _time;
    }

    public int getPodium() {return _podium;}

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
}
