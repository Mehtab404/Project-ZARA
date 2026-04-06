import java.util.*;
public class RepeatInstruction implements Instruction {
    private Expression repeatCount;
    private List<Instruction> body;
    public RepeatInstruction(Expression expression, List<Instruction> body) {
        this.repeatCount = expression;
        this.body = body;
    }
    
    @Override
    public void execute(Environment env) {
        Object val=repeatCount.evaluate(env);
        if(!(val instanceof Double)){ throw new RuntimeException("loop count must be a Number");}
        int loop=((Double) val).intValue();
        for(int i=0; i<loop;i++){
            for(Instruction ins:body){
                ins.execute(env);
            }
        }
        
    }
    
    
}
