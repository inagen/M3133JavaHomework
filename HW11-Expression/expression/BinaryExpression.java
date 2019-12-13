package expression;

public abstract class BinaryExpression implements AllExpression {
    final AllExpression first;
    final AllExpression second;
    String op;

    BinaryExpression(AllExpression first, AllExpression second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        BinaryExpression exp = (BinaryExpression) o;
        return this.first.equals(exp.first) && this.second.equals(exp.second);
    }

    protected double eval(double x) {
        switch (op) {
        case (" + "):
            return first.evaluate(x) + second.evaluate(x);
        case (" - "):
            return first.evaluate(x) - second.evaluate(x);
        case (" * "):
            return first.evaluate(x) * second.evaluate(x);
        case (" / "):
            return first.evaluate(x) / second.evaluate(x);
        default:
            return x;
        }
    }

    public final int evaluate(int x) {
        return (int) eval(x);
    }

    public final double evaluate(double x) {
        return eval(x);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return "(" + first.toString() + op + second.toString() + ")";
    }
}