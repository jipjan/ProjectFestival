package Events;
import java.io.Serializable;

public class Event implements GUI.MyPanel.ITableObject, Serializable {
    short _popularity;
    String _name;
    Time _time;


    /** constructor for an event planned or not
     * @param name        the name of the event
     * @param popularity  unidentified scale for popularity of the event
     * @param time        the begin and end date of the event in a Time object
     */
    public Event(String name, short popularity, Time time) {
        _name = name;
        _popularity = popularity;
        _time = time;
    }

    public short getPopularity() {
        return _popularity;
    }

    @Override
    public long getDuration() {
        return _time.getDurationInMinutes();
    }


    public String getName() {
        return _name;
    }

    public Time getTime() {
        return _time;
    }

    public void set_popularity(short _popularity) {
        this._popularity = _popularity;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_time(Time _time) {
        this._time = _time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return (getPopularity() == other.getPopularity()
                    && getName().equals(other.getName())
                    && getTime().equals(other.getTime())
            );
        }
        return false;
    }
}
