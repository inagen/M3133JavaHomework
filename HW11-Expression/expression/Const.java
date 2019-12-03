package expression;

public final class Const implements Expression {
    int value;

    Const(int value) {
        this.value = value;
    }

    public int evaluate(int x) {
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
        return Integer.toString(value);
    }
}