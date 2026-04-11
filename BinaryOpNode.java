
public class BinaryOpNode implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    private String operator;

    public BinaryOpNode(Expression leftExpression, String operator, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    @Override
    public Object evaluate(Environment env) {
        Object leftVal = leftExpression.evaluate(env);
        Object rightVal = rightExpression.evaluate(env);

        if (leftVal instanceof Double && rightVal instanceof Double) {
            double l = (Double) leftVal;
            double r = (Double) rightVal;
            switch (operator) {
                case "+":
                    return l + r;
                case "-":
                    return l - r;
                case "*":
                    return l * r;
                case "/":
                    if (r == 0)
                        throw new RuntimeException("Divide by zero not allowed");
                    return l / r;
                case ">":
                    return l > r;
                case "<":
                    return l < r;
                case "==":
                    return l == r;
                case "<=":
                    return l <= r;
                case ">=":
                    return l >= r;
                case "!=":
                    return l != r;

            }
        }
        if (operator.equals("+") && leftVal instanceof String && rightVal instanceof String) {
            return (String) leftVal + (String) rightVal;
        }
        throw new RuntimeException("Invalid operation: " + leftVal + " " + operator + " " + rightVal);
    }

}
