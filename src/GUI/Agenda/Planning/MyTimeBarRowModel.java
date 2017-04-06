package GUI.Agenda.Planning;

import de.jaret.util.date.Interval;
import de.jaret.util.ui.timebars.model.DefaultRowHeader;
import de.jaret.util.ui.timebars.model.DefaultTimeBarRowModel;
import de.jaret.util.ui.timebars.model.TimeBarRowHeader;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jaap-Jan on 21-3-2017.
 */
public class MyTimeBarRowModel extends DefaultTimeBarRowModel {

    public MyTimeBarRowModel(TimeBarRowHeader h){
        super(h);
    }

    @Override
    public void addInterval(Interval interval) {
        this._intervals.add(interval);
        Collections.sort(this._intervals, (i1, i2) -> i1.getBegin().compareTo(i2.getBegin()));
        if(this._minDate != null && this._intervals.size() != 1) {
            if(this._minDate.compareTo(interval.getBegin()) > 0) {
                this._minDate = interval.getBegin();
            }

            if(this._maxDate.compareTo(interval.getEnd()) < 0) {
                this._maxDate = interval.getEnd();
            }
        } else {
            this._minDate = interval.getBegin();
            this._maxDate = interval.getEnd();
        }

        interval.addPropertyChangeListener(this);
        this.fireElementAdded(interval);
    }
}
