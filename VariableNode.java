public class VariableNode implements Expression{
    private final String variable;

    public VariableNode(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    @Override
    public Object evaluate(Environment env) {
        return env.get(variable);
    }
}
