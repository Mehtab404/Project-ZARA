package ZARA;

public class NumberNode implements Expression{
    private Double value;
    public NumberNode(double value){
        this.value=value;
    }
    @Override
    public Object evaluate(Environment env) {
        return value;
    }

}