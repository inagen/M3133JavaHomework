package expression;

public final class Multiply extends BinaryExpression {
    Multiply(AllExpression first, AllExpression second) {
        super(first, second);
        op = " * ";
    }
}