package Events;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time implements Serializable {
    Date _beginDate;
    Date _endDate;

    public Time(Date begin, Date end) {
        _beginDate = begin;
        _endDate = end;
    }

    public Date getBeginDate() {
        return _beginDate;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public long getDurationInMinutes() {
        long deltaTime = _endDate.getTime() - _beginDate.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(deltaTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            Time other = (Time) obj;
            return other.getBeginDate().equals(getBeginDate()) && other.getEndDate().equals(getEndDate());
        }
        return false;
    }
}