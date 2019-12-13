package expression;

public final class Divide extends BinaryExpression {
    Divide(AllExpression first, AllExpression second) {
        super(first, second);
        op = " / ";
    }
}