import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {
    private final String xml;

    public XmlParser(String xml) {
        this.xml = xml;
    }

    public List<XmlNode> parse() {
        Pattern openingTagPattern = Pattern.compile("<\\w.+?>");
        Pattern closingTagPattern = Pattern.compile("</\\w+>");
        Pattern tagBodyPattern = Pattern.compile(">.+?<");
        Pattern emptyTagPattern = Pattern.compile("<\\w.+?/>");

        List<XmlNode> xmlNodes = new ArrayList<>();
        List<String> lines = splitLines();

        lines.forEach((line) -> {
            Matcher openingTagMatcher = openingTagPattern.matcher(line);
            Matcher closingTagMatcher = closingTagPattern.matcher(line);
            Matcher tagBodyMatcher = tagBodyPattern.matcher(line);
            Matcher emptyTagMatcher = emptyTagPattern.matcher(line);

            if (emptyTagMatcher.find()) {
                String body = emptyTagMatcher.group();
                xmlNodes.add(new XmlNode(body, NodeType.TAG_WITHOUT_BODY));
            } else if (openingTagMatcher.find()) {
                String body = openingTagMatcher.group();
                xmlNodes.add(new XmlNode(body, NodeType.OPENING));
            }
            if (tagBodyMatcher.find()) {
                String body = tagBodyMatcher.group().replaceAll("[<>]", "");
                xmlNodes.add(new XmlNode(body, NodeType.TAG_CONTENT));
            } else if (closingTagMatcher.find()) {
                String body = closingTagMatcher.group();
                xmlNodes.add(new XmlNode(body, NodeType.CLOSING));
            }
        });
        return xmlNodes;
    }

    private List<String> splitLines() {
        return List.of(xml.split("\n\\s*"));
    }
}
