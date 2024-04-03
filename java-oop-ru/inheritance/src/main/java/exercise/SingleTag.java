package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    private String name;
    private Map<String, String> atributs;

    public SingleTag(String name, Map<String, String> atributs) {
        this.name = name;
        this.atributs = atributs;
    }

    @Override
    public String toString() {
        String attributes = atributs.entrySet().stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));

        if (attributes.isEmpty()) {
            return String.format("<%s>", name);
        } else {
            return String.format("<%s %s>", name, attributes);
        }
    }
}
// END
