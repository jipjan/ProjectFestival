package Events;

import de.jaret.util.date.Interval;
import de.jaret.util.date.JaretDate;
import de.jaret.util.misc.PropertyObservableBase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Event extends PropertyObservableBase implements GUI.MyPanel.ITableObject, Serializable, Interval, PropertyChangeListener {
    private short _popularity;
    private String _performer;
    private String _name;
    private int _podium = -1;
    private Time _time;
    public boolean isScagueld;

    public Event(String name, String performer, short popularity, Time time) {
        _name = name;
        _popularity = popularity;
        _performer = performer;
        _time = time;
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
        setTime(new Time(_time.getBeginDate(), _time.getBeginDate().copy().advanceMinutes(duration)));
    }

    public String getPerformer() {
        return _performer;
    }

    public void setPerformer(String name) {
        _performer = name;
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
        setBegin(_time.getBeginDate());
        setEnd(_time.getEndDate());
    }

    public int getPodium() {return _podium;}

    public void setPodium(int pod) {
        _podium = pod;
    }

    @Override
    public void setBegin(JaretDate jaretDate) {
        if(getBegin() != null) {
            getBegin().removePropertyChangeListener(this);
        }

        JaretDate oldVal = getBegin();
        _time.setBeginDate(jaretDate);
        getBegin().addPropertyChangeListener(this);
        this.firePropertyChange("Begin", oldVal, jaretDate);
    }

    @Override
    public JaretDate getBegin() {
        return _time.getBeginDate();
    }

    @Override
    public void setEnd(JaretDate jaretDate) {
        if(getEnd() != null) {
            getEnd().removePropertyChangeListener(this);
        }

        JaretDate oldVal = getEnd();
        _time.setEndDate(jaretDate);
        getEnd().addPropertyChangeListener(this);
        this.firePropertyChange("End", oldVal, jaretDate);
    }

    @Override
    public JaretDate getEnd() {
        return _time.getEndDate();
    }

    @Override
    public boolean contains(JaretDate date) {
        return this.getBegin().compareTo(date) <= 0 && this.getEnd().compareTo(date) >= 0;
    }

    @Override
    public boolean contains(Interval interval) {
        return this.getBegin().compareTo(interval.getBegin()) >= 0 && this.getEnd().compareTo(interval.getEnd()) >= 0;
    }

    public int getSeconds() {
        return this.getEnd().diffSeconds(this.getBegin());
    }

    @Override
    public boolean intersects(Interval interval) {
        return intersect(this, interval);
    }

    public static boolean intersect(Interval i1, Interval i2) {
        return !i1.contains(i2.getBegin()) && !i1.contains(i2.getEnd())?i2.contains(i1.getBegin()) || i2.contains(i1.getEnd()):true;
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
        return _name + ": " + getBegin().toDisplayStringTime() + " - " + getEnd().toDisplayStringTime();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() == getBegin()) {
            this.firePropertyChange("Begin", evt.getOldValue(), evt.getNewValue());
        } else if(evt.getSource() == getEnd()) {
            this.firePropertyChange("End", evt.getOldValue(), evt.getNewValue());
        }
    }
}
