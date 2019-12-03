package expression;

public final class Add extends BinaryExpression {
    Add(Expression first, Expression second) {
        super(first, second);
    }

    public final int evaluate(int x) {
        return first.evaluate(x) + second.evaluate(x);
    }

    @Override
    public final String toString() {
        return "(" + first.toString() + " + " + second.toString() + ")";
    }
}