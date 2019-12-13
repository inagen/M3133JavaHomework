package expression;

public final class Add extends BinaryExpression {
    Add(AllExpression first, AllExpression second) {
        super(first, second);
        op = " + ";
    }
}