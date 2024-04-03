package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String name;
    private Map<String, String> atributs;
    private String body;
    private List<Tag> list;

    public PairedTag(String name, Map<String, String> atributs, String body, List<Tag> list) {
        this.name = name;
        this.atributs = atributs;
        this.body = body;
        this.list = list;
    }

    @Override
    public String toString() {
        String attributes = atributs.entrySet().stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));

        String formatting = String.format("<%s %s>%s</%s>", name, attributes, body, name);
        if (attributes.isEmpty()) {
            return String.format("<%s></%s>", name, name);
        } else if (list.isEmpty()) {
            return formatting;
        } else {
            String list2 = list.stream()
                    .map(Tag::toString)
                    .collect(Collectors.joining());
            String result = String.format("<%s %s>%s</%s>", name, attributes, list2, name);
            return result;
        }
    }
}
// END
