package expression;

public final class Divide extends BinaryExpression {
    Divide(Expression first, Expression second) {
        super(first, second);
    }

    public final int evaluate(int x) {
        return first.evaluate(x) / second.evaluate(x);
    }

    public final String toString() {
        return "(" + first.toString() + " / " + second.toString() + ")";
    }
}