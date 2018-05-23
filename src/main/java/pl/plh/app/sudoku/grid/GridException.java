package pl.plh.app.sudoku.grid;

public class GridException extends RuntimeException {
    public GridException(String message) {
        super(message);
    }

    public GridException() {
        super();
    }
}
