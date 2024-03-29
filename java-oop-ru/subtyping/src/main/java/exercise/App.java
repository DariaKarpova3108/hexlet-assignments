package exercise;

import java.util.*;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> originalMap = storage.toMap();
        Set<Entry<String, String>> swappedMap = originalMap.entrySet();

        swappedMap.forEach(entry -> storage.unset(entry.getKey()));
        swappedMap.forEach(entry -> storage.set(entry.getValue(), entry.getKey()));
    }
}
// END
