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


    /**
     * constructor for Time for a Event that is not yet planned in
     *
     * @param minutes the minutes of how long the event takes Canot be longer than a day (glitch will occur)
     */
    public Time(int minutes)
    {
        int beginYear = 0, beginMounth = 0, beginDate = 0, beginHour =0, beginminute = 0; //the standard time of not yet planned Event
        _beginDate = new Date(beginYear ,beginMounth,beginDate,beginHour, beginminute);

        int endMinute=+ minutes % 60;
        int endHour = minutes/60;
        _endDate = new Date(beginYear, beginMounth, beginDate, endHour, endMinute);
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