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
            if (Character.isLetter(ch)) {
                tokenList.add(readWord());
                continue;
            }

            if (Character.isWhitespace(ch)) {
                pos++;
                continue;
            }
            switch (ch) {
                case '+':
                    tokenList.add(new Token(TokenType.SUM, "+", line));
                    break;
                case '-':
                    tokenList.add(new Token(TokenType.SUB, "-", line));
                    break;
                case '*':
                    tokenList.add(new Token(TokenType.MUL, "*", line));
                    break;
                case '/':
                    tokenList.add(new Token(TokenType.DIVIDE, "/", line));
                    break;

                case '=':
                    if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') {
                        tokenList.add(new Token(TokenType.EQUAL_EQUAL, "==", line));
                        pos += 2;
                        continue;
                    } else {
                        tokenList.add(new Token(TokenType.EQUAL, "=", line));
                    }
                    break;

                case '>':
                    if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') {
                        tokenList.add(new Token(TokenType.GREATER_EQUAL, ">=", line));
                        pos += 2;
                        continue;
                    } else {
                        tokenList.add(new Token(TokenType.GREATER, ">", line));
                    }
                    break;
                case '<':
                    if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') {
                        tokenList.add(new Token(TokenType.LESS_EQUAL, "<=", line));
                        pos += 2;
                        continue;
                    } else {
                        tokenList.add(new Token(TokenType.LESS, "<", line));
                    }
                    break;

                case '!':
                    if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') {
                        tokenList.add(new Token(TokenType.NOT_EQUAL, "!=", line));
                        pos += 2;
                        continue;
                    }
                    throw new RuntimeException("Unexpexted charactter !");
                case ':':
                    tokenList.add(new Token(TokenType.COLON, ":", line));
                    break;
                default:
                    throw new RuntimeException("Unknown character: " + ch);
            }
            pos++;
        }
        tokenList.add(new Token(TokenType.EOF, "", line));
        return tokenList;
    }
    private Token readNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < source.length() && (Character.isDigit(source.charAt(pos))|| source.charAt(pos)=='.')) {
            sb.append(source.charAt(pos));
            pos++;
        }
        return new Token(TokenType.NUMBER, sb.toString(), line);
    }
    
    private Token readWord() {
        StringBuilder sb = new StringBuilder();
        while (pos < source.length() && Character.isLetterOrDigit(source.charAt(pos))) {
            sb.append(source.charAt(pos));
            pos++;
        }
        String word = sb.toString().toLowerCase();
        if (word.equals("set"))
            return new Token(TokenType.SET, "set", line);
        if (word.equals("show"))
            return new Token(TokenType.SHOW, "show", line);
        if (word.equals("when"))
            return new Token(TokenType.WHEN, "when", line);
        if (word.equals("loop"))
            return new Token(TokenType.LOOP, "loop", line);

        return new Token(TokenType.IDENTIFIER, word, line);
    }

}   
