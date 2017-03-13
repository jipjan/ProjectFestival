package Events;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time implements Serializable {
    Date _beginDate;
    Date _endDate;

    public Time(int time) {
        Date beginDate = new Date();
        Date endDate = new Date();
        _beginDate = beginDate;
        endDate.setTime(_beginDate.getTime() + time * 60 *1000);
        _endDate = endDate;

    }

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
            return inRange(getBeginDate(), other.getBeginDate(), 5000)
                    && inRange(getEndDate(), other.getEndDate(), 5000);
        }
        return false;
    }

    private boolean inRange(Date d1, Date d2, int range) {
        return d1.getTime() >= d2.getTime() - range && d1.getTime() <= d2.getTime() + range;
    }
}