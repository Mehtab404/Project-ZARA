
public class Token {
    private final TokenType type;
    private final String value;
    private final int lineNo;
    
    public Token(TokenType type, String value, int lineNo) {
        this.type = type;
        this.value = value;
        this.lineNo = lineNo;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    public int getLineNo() {
        return lineNo;
    }
}

