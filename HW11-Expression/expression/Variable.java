package expression;

public final class Variable implements AllExpression {
    String name;

    Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Variable exp = (Variable) o;
        return this.toString().equals(exp.toString());
    }

    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}