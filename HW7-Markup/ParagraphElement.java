package markup;

public interface ParagraphElement extends Markable {
    void toMarkdown(StringBuilder result);
}