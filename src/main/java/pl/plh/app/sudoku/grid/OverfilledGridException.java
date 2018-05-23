package pl.plh.app.sudoku.grid;

public class OverfilledGridException extends GridException {
    public OverfilledGridException(String message) {
        super(message);
    }

    public OverfilledGridException() {
        super();
    }
}
