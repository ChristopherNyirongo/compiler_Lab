class Parser {

    private Lexer lexer;
    private Token currentToken;
    private SymbolTable symbolTable;
    private TACGenerator tac;

    // NEW: for postfix output
    private StringBuilder postfix = new StringBuilder();

    Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
        this.symbolTable = new SymbolTable();
        this.tac = new TACGenerator();
    }

    // Match expected token
    void eat(String tokenType) {
        if (currentToken.type.equals(tokenType)) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException(
                "Syntax Error: Expected " + tokenType +
                ", got " + currentToken.type
            );
        }
    }

    // expr → term rest
    String expr() {
        String left = term();
        return rest(left);
    }

    // rest → + term rest | - term rest | ε
    String rest(String left) {

        if (currentToken.type.equals("PLUS")) {
            eat("PLUS");
            String right = term();

            // TAC
            String temp = tac.newTemp();
            tac.emit(temp, left, "+", right);

            // POSTFIX
            postfix.append("+ ");

            return rest(temp);
        }

        else if (currentToken.type.equals("MINUS")) {
            eat("MINUS");
            String right = term();

            // TAC
            String temp = tac.newTemp();
            tac.emit(temp, left, "-", right);

            // POSTFIX
            postfix.append("- ");

            return rest(temp);
        }

        return left; // epsilon
    }

    // term → factor term_tail
    String term() {
        String left = factor();
        return term_tail(left);
    }

    // term_tail → * factor term_tail | / factor term_tail | ε
    String term_tail(String left) {

        if (currentToken.type.equals("MUL")) {
            eat("MUL");
            String right = factor();

            // TAC
            String temp = tac.newTemp();
            tac.emit(temp, left, "*", right);

            // POSTFIX
            postfix.append("* ");

            return term_tail(temp);
        }

        else if (currentToken.type.equals("DIV")) {
            eat("DIV");
            String right = factor();

            // TAC
            String temp = tac.newTemp();
            tac.emit(temp, left, "/", right);

            // POSTFIX
            postfix.append("/ ");

            return term_tail(temp);
        }

        return left; // epsilon
    }

    // factor → (expr) | NUM | ID
    String factor() {

        if (currentToken.type.equals("LPAREN")) {
            eat("LPAREN");
            String result = expr();
            eat("RPAREN");
            return result;
        }

        else if (currentToken.type.equals("NUM")) {
            String value = currentToken.value;

            // POSTFIX
            postfix.append(value).append(" ");

            eat("NUM");
            return value;
        }

        else if (currentToken.type.equals("ID")) {
            String mapped = symbolTable.getSymbol(currentToken.value);

            // POSTFIX
            postfix.append(mapped).append(" ");

            eat("ID");
            return mapped;
        }

        else {
            throw new RuntimeException(
                "Syntax Error in factor at token: " +
                currentToken.type + " (" + currentToken.value + ")"
            );
        }
    }

    // Getter for postfix
    public String getPostfix() {
        return postfix.toString();
    }

    // Getter for Symbol Table
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    // Getter for TAC
    public TACGenerator getTAC() {
        return tac;
    }

    // Getter for current token (for EOF check)
    public Token getCurrentToken() {
        return currentToken;
    }
}