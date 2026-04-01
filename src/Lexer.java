class Lexer {

    private String text;
    private int pos;
    private char currentChar;

    Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = text.charAt(pos);
    }

    void advance() {
        pos++;
        if (pos < text.length()) {
            currentChar = text.charAt(pos);
        } else {
            currentChar = '\0';
        }
    }

    void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    Token number() {
        String result = "";
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result += currentChar;
            advance();
        }
        return new Token("NUM", result);
    }

    Token identifier() {
        String result = "";
        while (currentChar != '\0' && Character.isLetter(currentChar)) {
            result += currentChar;
            advance();
        }
        return new Token("ID", result);
    }

    Token getNextToken() {

        while (currentChar != '\0') {

            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            if (Character.isDigit(currentChar)) {
                return number();
            }

            if (Character.isLetter(currentChar)) {
                return identifier();
            }

            if (currentChar == '+') {
                advance();
                return new Token("PLUS", "+");
            }

            if (currentChar == '-') {
                advance();
                return new Token("MINUS", "-");
            }

            if (currentChar == '*') {
                advance();
                return new Token("MUL", "*");
            }

            if (currentChar == '/') {
                advance();
                return new Token("DIV", "/");
            }

            if (currentChar == '(') {
                advance();
                return new Token("LPAREN", "(");
            }

            if (currentChar == ')') {
                advance();
                return new Token("RPAREN", ")");
            }

            throw new RuntimeException("Invalid character: " + currentChar);
        }

        return new Token("EOF", null);
    }
}