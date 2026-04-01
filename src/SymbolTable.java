import java.util.LinkedHashMap;

class SymbolTable {

    private LinkedHashMap<String, String> table;
    private int counter;

    SymbolTable() {
        table = new LinkedHashMap<>();
        counter = 1;
    }

    String getSymbol(String name) {
        if (!table.containsKey(name)) {
            String newName = "v" + counter;
            table.put(name, newName);
            counter++;
        }
        return table.get(name);
    }

    @Override
    public String toString() {
        String result = "Symbol Table:\n";
        for (String key : table.keySet()) {
            result += key + " → " + table.get(key) + "\n";
        }
        return result;
    }
}