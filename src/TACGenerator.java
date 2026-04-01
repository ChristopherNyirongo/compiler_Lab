import java.util.ArrayList;

class TACGenerator {

    private ArrayList<String> code;
    private int tempCount;

    TACGenerator() {
        code = new ArrayList<>();
        tempCount = 1;
    }

    // Generate new temporary variable (t1, t2, t3...)
    String newTemp() {
        return "t" + tempCount++;
    }

    // Add instruction to TAC list
    void emit(String result, String arg1, String op, String arg2) {
        code.add(result + " = " + arg1 + " " + op + " " + arg2);
    }

    // Print all generated TAC
    void printCode() {
        System.out.println("Three Address Code:");
        for (String line : code) {
            System.out.println(line);
        }
    }
}