package exercise;

// BEGIN

import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String path;

    public FileKV(String path, Map<String, String> initial) {
        this.path = path;
        initial.forEach(this::set);
    }

    @Override
    public void set(String key, String value) {
        String content = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(content);
        map.put(key, value);
        Utils.writeFile(path, Utils.serialize(map));

    }

    @Override
    public void unset(String key) {
        String content = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(content);
        map.remove(key);
        Utils.writeFile(path, Utils.serialize(map));

    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(content);
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String content = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(content);
        return map;
    }
}
// END
