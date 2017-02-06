package Map;

import java.util.spi.LocaleServiceProvider;

public class Entity {
    EntityType _type;
    Location _location;

    public Entity(EntityType type, Location location) {
        _type = type;
        _location = location;
    }

    public EntityType getType() {
        return _type;
    }

    public Location getLocation() {
        return _location;
    }
}
