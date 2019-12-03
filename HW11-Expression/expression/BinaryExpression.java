package expression;

public abstract class BinaryExpression implements Expression {
    final Expression first;
    final Expression second;

    BinaryExpression(Expression first, Expression second) {
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
        return this.toString().equals(exp.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}