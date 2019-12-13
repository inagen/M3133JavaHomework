package expression;

public final class Const implements AllExpression {
    double value;
    boolean isInt;

    Const(int value) {
        this.isInt = true;
        this.value = value;
    }

    Const(double value) {
        this.isInt = false;
        this.value = value;
    }

    public int evaluate(int x) {
        return (int) value;
    }

    public double evaluate(double x) {
        return value;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Const exp = (Const) o;
        return this.toString().equals(exp.toString());
    }

    public String toString() {
        return (isInt ? (Integer.toString((int) value)) : Double.toString(value));
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}