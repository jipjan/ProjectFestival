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

    public void set_popularity(short _popularity) {
        this._popularity = _popularity;
    }

    public void set_performer(String performer) {
        this._performer = performer;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public void set_podium(int podium) {
        this._podium = podium;
    }

    public void set_time(Time time) {
        this._time = time;
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
}
