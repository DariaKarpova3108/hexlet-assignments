package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String type;
    TagInterface tag;

    public LabelTag(String type, TagInterface tag) {
        this.type = type;
        this.tag = tag;
    }

    @Override
    public String render() {
        return "<label>" + type + tag.render() + "</label>";
    }
}
// END
