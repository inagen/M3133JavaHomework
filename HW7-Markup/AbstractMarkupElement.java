package markup;

import java.util.list;

abstract class AbstractMarkupElement {
    List<ParagraphElement> elements;

    AbstractMarkupElement(List<ParagraphElement> elements) {
        this.elements = elements;
    }

    void toMarkdown(StringBuilder result, String border) {
        result.append(border);
        for (ParagraphElement elem : elements) {
            elem.toMarkdown(result);
        }
        result.append(border);
    }

    void toHtml(StringBuilder result, String borderBegin, String borderEnd) {
        result.append(borderBegin);
        for (ParagraphElement elem : elements) {
            elem.toHtml(result);
        }
        result.append(borderEnd);
    }

}