package expression;

public final class Multiply extends BinaryExpression {
    Multiply(Expression first, Expression second) {
        super(first, second);
    }

    public final int evaluate(int x) {
        return first.evaluate(x) * second.evaluate(x);
    }

    public final String toString() {
        return "(" + first.toString() + " * " + second.toString() + ")";
    }
}