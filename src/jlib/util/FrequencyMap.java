package jlib.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Moop on 2016-01-01.
 *
 */
public class FrequencyMap<T> {

    private final Map<T, Integer> map = new LinkedHashMap<>();
    private int count = 0;

    public FrequencyMap() { }

    @SafeVarargs
    public FrequencyMap(T... items) {
        add(items);
    }

    @SafeVarargs
    public final void add(T... items) {
        count += items.length;
        for (T item : items) {
            Integer item_count = map.get(item);
            if (item_count == null) {
                map.put(item, 1);
            } else {
                map.put(item, item_count + 1);
            }
        }
    }

    public double frequency(T value) {
        Integer item_count = map.get(value);
        return ((item_count == null) ? (0.0) : (item_count/(double)count));
    }

    public int items() {
        return (count);
    }

    @SafeVarargs
    public final void set(T... items) {
        count = 0;
        map.clear();
        add(items);
    }
}
