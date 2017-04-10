package HelperClasses;

import java.util.HashMap;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class PairingHashMap<V> {
    HashMap<Double, V> _map;

    public PairingHashMap() {}

    public PairingHashMap(int size) {
        _map = new HashMap<>(size);
    }

    public void add(int x, int y, V id) {
        _map.put(paring(x, y), id);
    }

    public V get(int x, int y) {
        return _map.get(paring(x, y));
    }

    private double paring(int x, int y) {
        return 0.5*(x + y)*(x + y + 1) + y;
    }

    public int size() { return _map.size(); }
}
