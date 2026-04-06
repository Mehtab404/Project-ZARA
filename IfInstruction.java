import java.util.*;

public class IfInstruction implements Instruction{
    private Expression expression;
    List<Instruction> body=new ArrayList<>();
    

    public IfInstruction(Expression expression, List<Instruction> body) {
        this.expression = expression;
        this.body = body;
    }


    @Override
    public void execute(Environment env) {
        boolean result=(boolean) expression.evaluate(env);// we can enhance more;
        if(result){
            for (Instruction ins: body){
                ins.execute(env);
            }

        }
        
    }


}
