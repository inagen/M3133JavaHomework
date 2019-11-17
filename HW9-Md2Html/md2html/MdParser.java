package md2html;

import java.util.*;

public class MdParser {
    private String text = null;
    private Stack<String> tags = new Stack<>();
    private int tagsNumber = 7;
    private String[] mdTags = new String[] { "**", "__", "--", "++", "*", "_", "`" };
    private String[] htmlTags = new String[] { "strong", "strong", "s", "u", "em", "em", "code" };

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
        ArrayList<ArrayList<Integer>> tagPositions = new ArrayList<>();
        for (int i = 0; i < tagsNumber; i++) {
            tagPositions.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < text.length(); i++) {
            String tag = text.substring(i, Math.min(i + 2, text.length()));
            if (text.charAt(i) == '\\') {
                i++;
                continue;
            }

            int id;
            if ((id = getTagId(tag)) != -1) {
                tagPositions.get(id).add(i);
            }
        }

        for (int i = 0; i < tagsNumber; i++) {
            int s = tagPositions.get(i).size();
            if (s % 2 != 0) {
                tagPositions.get(i).remove(s - 1);
            }
        }
        int tagCounter[] = new int[tagsNumber];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String tag = text.substring(i, Math.min(i + 2, text.length()));
            switch (ch) {
            case '\\':
                continue;
            case '&':
                builder.append("&amp;");
                continue;
            case '>':
                builder.append("&gt;");
                continue;
            case '<':
                builder.append("&lt;");
                continue;
            }
            int tagId = getTagId(tag);
            if (tagId == -1 || tagPositions.get(tagId).isEmpty()) {
                builder.append(ch);
            } else {
                boolean opened = ((tagPositions.get(tagId).size() - tagCounter[tagId]) % 2 == 0);
                String tmp = htmlTags[tagId];
                int len = tag.length();
                String res = "<" + (opened ? "" : "/") + tmp + ">";
                builder.append(res);
                i += len - 1;
                tagCounter[tagId]++;
            }
        }
    }

    private int getTagId(String str) {
        int id = -1;
        for (int i = 0; i < tagsNumber; i++) {
            if (str.equals(mdTags[i])) {
                id = i;
            }
        }
        if (id == -1) {
            for (int i = 0; i < tagsNumber; i++) {
                if (mdTags[i].equals(Character.toString(str.charAt(0)))) {
                    id = i;
                }
            }
        }
        return id;
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

}