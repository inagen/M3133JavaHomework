package markup;

public class Text implements Markable {
    private String content;

    public Text(String content) {
        this.content = content;
    }

    @Override
    void toMarkdown(StringBuilder result) {
        result.append(content);
    }

    @Override
    void toHtml(StringBuilder result) {
        result.append(content);
    }
}