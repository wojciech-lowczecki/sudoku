package pl.plh.app.sudoku.grid;

public class MissingElementException extends GridException {
    public MissingElementException(int row, int column) {
        super(row + "," + column);
    }

    public MissingElementException() {
        super("unknown");
    }

    public MissingElementException(String message) {
        super(message);
    }
}
