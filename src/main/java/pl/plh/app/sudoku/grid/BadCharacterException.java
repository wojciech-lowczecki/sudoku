package pl.plh.app.sudoku.grid;

public class BadCharacterException extends GridException {
    public BadCharacterException(String message) {
        super(message);
    }

    public BadCharacterException(char ch) {
        super("'" + ch + "'");
    }

    public BadCharacterException() {
        super("unknown");
    }
}
