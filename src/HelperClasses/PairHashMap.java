package HelperClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class PairHashMap<K, V> {
    HashMap<K, HashMap<K, V>> _map;

    private int _size = 0;

    public PairHashMap() { _map = new HashMap<>(); }

    public PairHashMap(int size) {
        _map = new HashMap<>(size);
    }

    public void add(K x, K y, V id) {
        if (_map.containsKey(x))
            _map.get(x).put(y, id);
        else {
            HashMap<K, V> yMap = new HashMap<>();
            yMap.put(y, id);
            _map.put(x, yMap);
        }
        _size++;
    }

    public V get(int x, int y) {
        if (_map.containsKey(x))
            return _map.get(x).get(y);
        else
            return null;
    }

    public int size() { return _size; }

    public Set<Map.Entry<K, HashMap<K, V>>> entrySet() {
        return _map.entrySet();
    }
}
