package markup;

import java.util.list;

public class Emphasis extends AbstractMarkupElement implements Markable {
    public Emphasis(List<ParagraphElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "*");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<em>", "</em>");
    }
}