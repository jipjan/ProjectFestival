//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GUI.Agenda.Planning;

import de.jaret.util.date.Interval;
import de.jaret.util.date.JaretDate;
import de.jaret.util.misc.PropertyObservableBase;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IntervalImpl extends PropertyObservableBase implements Interval, PropertyChangeListener {
    protected JaretDate _begin;
    protected JaretDate _end;

    public IntervalImpl() { }

    public JaretDate getBegin() {
        return this._begin;
    }

    public JaretDate getEnd() {
        return this._end;
    }

    public void setBegin(JaretDate begin) {
        if(this._begin != null) {
            this._begin.removePropertyChangeListener(this);
        }

        JaretDate oldVal = this._begin;
        this._begin = begin;
        this._begin.addPropertyChangeListener(this);
        this.firePropertyChange("Begin", oldVal, begin);
    }

    public void setEnd(JaretDate end) {
        if(this._end != null) {
            this._end.removePropertyChangeListener(this);
        }

        JaretDate oldVal = this._end;
        this._end = end;
        this._end.addPropertyChangeListener(this);
        this.firePropertyChange("End", oldVal, end);
    }

    public boolean contains(JaretDate date) {
        return this.getBegin().compareTo(date) <= 0 && this.getEnd().compareTo(date) >= 0;
    }

    public boolean contains(Interval interval) {
        return this.getBegin().compareTo(interval.getBegin()) >= 0 && this.getEnd().compareTo(interval.getEnd()) >= 0;
    }

    public static boolean containsStatic(Interval interval, JaretDate date) {
        return interval.getBegin().compareTo(date) <= 0 && interval.getEnd().compareTo(date) >= 0;
    }

    public int getSeconds() {
        return this.getEnd().diffSeconds(this.getBegin());
    }

    public boolean intersects(Interval interval) {
        return intersect(this, interval);
    }

    public static boolean intersect(Interval i1, Interval i2) {
        return !i1.contains(i2.getBegin()) && !i1.contains(i2.getEnd())?i2.contains(i1.getBegin()) || i2.contains(i1.getEnd()):true;
    }

    public static boolean intersectNonIncluding(Interval i1, Interval i2) {
        return !containsNonIncluding(i1, i2.getBegin()) && !containsNonIncluding(i1, i2.getEnd())?(!containsNonIncluding(i2, i1.getBegin()) && !containsNonIncluding(i2, i1.getEnd())?i1.getBegin().equals(i2.getBegin()) && i1.getEnd().equals(i2.getEnd()):true):true;
    }

    public static boolean containsNonIncluding(Interval interval, JaretDate date) {
        return interval.getBegin().compareTo(date) < 0 && interval.getEnd().compareTo(date) > 0;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() == this._begin) {
            this.firePropertyChange("Begin", evt.getOldValue(), evt.getNewValue());
        } else if(evt.getSource() == this._end) {
            this.firePropertyChange("End", evt.getOldValue(), evt.getNewValue());
        }

    }
}
