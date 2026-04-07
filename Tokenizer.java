import java.util.ArrayList;
import java.util.List;
public class Tokenizer {
    private final String source;
    private int pos;
    private int line = 1;

    public Tokenizer(String source) {
        this.source = source;
    }

    public List<Token> tokenize() {
        List<Token> tokenList = new ArrayList<>();
        while (pos < source.length()) {
            char ch = source.charAt(pos);
            if (ch == '\n') {
                tokenList.add(new Token(TokenType.NEWLINE, "\\n", line));
                line++;
                pos++;
                continue;
            }
            if (ch == '"') {
                tokenList.add(readString());
                continue;
            }

            if (Character.isDigit(ch)) {
                tokenList.add(readNumber());
                continue;
            }
        }
    
    }
}
