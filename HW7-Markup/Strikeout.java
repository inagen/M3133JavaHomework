package markup;

import java.util.list;

public class Strikeout extends AbstractMarkupElement implements Markable {
    public Strikeout(List<ParagraphElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<strong>", "</strong>");
    }
}