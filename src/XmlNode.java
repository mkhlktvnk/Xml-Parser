public class XmlNode {
    private final String body;
    private final NodeType tagType;

    public XmlNode(String body, NodeType tagType) {
        this.body = body;
        this.tagType = tagType;
    }

    public String getBody() {
        return body;
    }

    public NodeType getTagType() {
        return tagType;
    }

    @Override
    public String toString() {
        return "XmlNode{" +
                "body='" + body + '\'' +
                ", nodeType=" + tagType +
                '}';
    }
}
