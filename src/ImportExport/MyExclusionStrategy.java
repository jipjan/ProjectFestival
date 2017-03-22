package ImportExport;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by Jaap-Jan on 22-3-2017.
 */
public class MyExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return aClass == java.beans.PropertyChangeSupport.class;
    }
}
