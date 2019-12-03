package expression;

public class Main {
    public static void main(String[] args) {
        BinaryExpression expression = new Add(
                new Add(new Multiply(new Variable("x"), new Variable("x")), new Variable("x")), new Const(1));
        System.out.println(expression.evaluate(Integer.parseInt(args[0])));
        System.out.println(
                new Add(new Variable("x"), new Variable("x")).equals(new Add(new Variable("x"), new Variable("x"))));
        System.out.println(new Add(new Variable("x"), new Const(2)).toString());
    }
}