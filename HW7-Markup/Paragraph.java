package markup;

import java.util.list;

public class Paragraph extends AbstractMarkupElement implements Markable {
    public Paragraph(List<ParagraphElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "", "");
    }
}