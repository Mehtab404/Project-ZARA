import java.util.*;

public class Parser {
    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Instruction> parse() {
        List<Instruction> instructions = new ArrayList<>();

        while (!isAtEnd()) {
            if (match(TokenType.NEWLINE))
                continue;
            instructions.add(parseStatement());
        }

        return instructions;
    }

    private Instruction parseStatement() {

        if (match(TokenType.SET))
            return parseAssign();

        if (match(TokenType.SHOW))
            return parsePrint();

        if (match(TokenType.WHEN))
            return parseIf();

        if (match(TokenType.LOOP))
            return parseLoop();

        throw new RuntimeException("Unexpected token: " + peek().getValue());
    }

        private Instruction parseAssign() {
        Token name = consume(TokenType.IDENTIFIER, "Expected variable name");
        consume(TokenType.EQUAL, "Expected '='");

        Expression expr = parseExpression();
        return new AssignInstruction(name.getValue(), expr);
    }

        private Instruction parsePrint() {
        Expression expr = parseExpression();
        return new PrintInstruction(expr);
    }

     private Instruction parseIf() {
        Expression condition = parseExpression();
        consume(TokenType.COLON, "Expected ':'");
        consume(TokenType.NEWLINE, "Expected newline");

        List<Instruction> body = new ArrayList<>();

        while(!check(TokenType.DEDENT)&& !isAtEnd()){
            if (match(TokenType.NEWLINE)) continue;
            body.add(parseStatement());
        }

        return new IfInstruction(condition, body);
    }

        private Instruction parseLoop() {
        Expression count = parseExpression();
        consume(TokenType.COLON, "Expected ':'");
        consume(TokenType.NEWLINE, "Expected newline");

        List<Instruction> body = new ArrayList<>();

        while(!check(TokenType.DEDENT)&&!isAtEnd()){
            if(match(TokenType.NEWLINE)) continue;
            body.add(parseStatement());
        }

        return new RepeatInstruction(count, body);
    }
    private Expression parseExpression() {
        return null;
    }



    private boolean isAtEnd() {
        return tokens.get(current).getType() == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean check(TokenType type) {
        if (isAtEnd())
            return false;
        return peek().getType() == type;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                current++;
                return true;
            }
        }
        return false;
    }
        private Token consume(TokenType type, String msg) {
        if (check(type)) return tokens.get(current++);
        throw new RuntimeException(msg);
    }
    
}