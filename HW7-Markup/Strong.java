package markup;

import java.util.list;

public class Strong extends AbstractMarkupElement implements Markable {
    public Strong(List<ParagraphElement> elements) {
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