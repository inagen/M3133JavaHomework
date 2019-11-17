package md2html;

import java.util.*;

public class MdParser {
    private String text = null;
    private Stack<String> tags = new Stack<>();
    private String[] mdTags = new String[] { "**", "__", "--", "*", "_", "`" };
    private String[] htmlTagsB = new String[] { "<strong>", "<strong>", "<s>", "<em>", "<em>", "<code>" };
    private String[] htmlTagsE = new String[] { "</strong>", "</strong>", "</s>", "</em>", "</em>", "</code>" };

    public MdParser(String text) {
        this.text = text;
    }

    public void toHtml(StringBuilder builder) {
        if (isHeader(text)) {
            int lvl = getHeaderLevel(text);
            text = text.substring(lvl + 1);
            builder.append("<h" + lvl + ">");
            parse(builder);
            builder.append("</h" + lvl + ">");
        } else {
            builder.append("<p>");
            parse(builder);
            builder.append("</p>");
        }
    }

    private void parse(StringBuilder builder) {
        for (int i = 0; i < text.length(); i++) {
            String tag;
            if (i + 2 < text.length()) {
                tag = text.substring(i, i + 2);
            } else {
                tag = text.substring(i, text.length());
            }
            if (parseTag(tag, builder)) {
                i++;
                continue;
            } else if (parseTag(tag.substring(0, tag.length() - 1), builder)) {
                continue;
            } else if (text.charAt(i) == '\\') {
                if (i < text.length() - 1) {
                    builder.append(text.charAt(i + 1));
                    i++;
                }
                continue;
            } else if (text.charAt(i) == '<') {
                builder.append("&lt;");
            } else if (text.charAt(i) == '>') {
                builder.append("&gt;");
            } else if (text.charAt(i) == '&') {
                builder.append("&amp;");
            } else {
                builder.append(text.charAt(i));
            }
        }
    }

    private boolean parseTag(String tag, StringBuilder builder) {
        int id;
        if ((id = getTagId(tag)) != -1) {
            if (tags.isEmpty() || !tags.peek().equals(tag)) {
                tags.push(tag);
                builder.append(htmlTagsB[id]);
            } else {
                tags.pop();
                builder.append(htmlTagsE[id]);
            }
            return true;
        }
        return false;
    }

    private boolean isHeader(String str) {
        int level = getHeaderLevel(str);
        return level > 0 && level < str.length() && str.charAt(level) == ' ';
    }

    private int getHeaderLevel(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    private int getTagId(String str) {
        for (int i = 0; i < 6; i++) {
            if (str.equals(mdTags[i])) {
                return i;
            }
        }
        return -1;
    }
}