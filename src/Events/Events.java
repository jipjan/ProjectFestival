package Events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Events extends ArrayList<Event> implements Serializable {
    public Events(List<Event> events) {
        super(events);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Events) {
            Events other = (Events) obj;
            if (other.size() == size()) {
                for (int i = 0; i < size(); i++)
                    if (!get(i).equals(other.get(i)))
                        return false;
                return true;
            }
        }
        return false;
    }
}