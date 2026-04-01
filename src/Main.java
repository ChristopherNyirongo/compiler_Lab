public class Main {

    public static void main(String[] args) {

        String input = "a + b * (c + 2)";

        // =========================
        // 1. INPUT
        // =========================
        System.out.println("Input: " + input);

        // =========================
        // 2. LEXICAL ANALYSIS (TOKENS)
        // =========================
        System.out.println("\nTokens:");

        Lexer tempLexer = new Lexer(input);
        Token token = tempLexer.getNextToken();

        while (!token.type.equals("EOF")) {
            System.out.println(token.type + " : " + token.value);
            token = tempLexer.getNextToken();
        }

        // =========================
        // 3. PARSING + TRANSLATION
        // =========================
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        parser.expr();

        if (!parser.getCurrentToken().type.equals("EOF")) {
            throw new RuntimeException("Unexpected token at end");
        }

        // =========================
        // 4. POSTFIX OUTPUT
        // =========================
        System.out.println("\nPostfix Output:");
        System.out.println(parser.getPostfix());

        // =========================
        // 5. SYMBOL TABLE
        // =========================
        System.out.println(parser.getSymbolTable());

        // =========================
        // 6. THREE-ADDRESS CODE
        // =========================
        parser.getTAC().printCode();
    }
}