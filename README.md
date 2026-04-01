# Compiler Lab Project

## Description
This project is a **Java-based compiler lab** designed to help students understand the principles of lexical analysis, parsing, and intermediate code generation.  
It includes a lexer, parser, symbol table management, and three-address code generation for simple arithmetic expressions.

---

## Features
- **Lexer**: Breaks input strings into tokens.
- **Parser**: Checks syntax and generates parse trees.
- **Symbol Table**: Tracks variable names and values.
- **Three-Address Code (TAC)**: Generates intermediate representation for expressions.
- **Error Handling**: Detects invalid syntax and unexpected tokens.

---

## Files in this Repository
- `Main.java` – Entry point of the program
- `Lexer.java` – Tokenizer for input expressions
- `Parser.java` – Syntax analyzer and TAC generator
- Other Java files – Support classes for symbol table and code management

---

## How to Run
1. Make sure you have **Java JDK** installed.
2. Open the project folder in **VS Code** or any IDE.
3. Compile the Java files:
   ```bash
   javac *.java