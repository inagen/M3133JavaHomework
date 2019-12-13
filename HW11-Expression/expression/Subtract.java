package expression;

public final class Subtract extends BinaryExpression {
    Subtract(AllExpression first, AllExpression second) {
        super(first, second);
        op = " - ";
    }
}