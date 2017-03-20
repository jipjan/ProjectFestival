package Events;

import de.jaret.util.date.JaretDate;

import java.io.Serializable;
import java.util.Date;

public class Time implements Serializable {
    JaretDate _beginDate;
    JaretDate _endDate;

    public Time(int time) {
        JaretDate beginDate = new JaretDate();
        JaretDate endDate = new JaretDate().advanceMinutes(time);
        _beginDate = beginDate;
        _endDate = endDate;

    }

    public Time(Date begin, Date end) {
        _beginDate = new JaretDate(begin);
        _endDate = new JaretDate(end);
    }

    public JaretDate getBeginDate() {
        return _beginDate;
    }

    public JaretDate getEndDate() { return _endDate; }

    public long getDurationInMinutes() {
        return (long) _endDate.diffMinutes(_beginDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            Time other = (Time) obj;
            return _beginDate.diffMinutes(other.getBeginDate()) < 1 && _endDate.diffMinutes(other.getEndDate()) < 1;
        }
        return false;
    }
}