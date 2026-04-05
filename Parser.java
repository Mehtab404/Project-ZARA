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
            if (match(TokenType.NEWLINE)) continue;
            instructions.add(parseStatement());
        }

        return instructions;
    }
}