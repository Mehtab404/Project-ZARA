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
    
}