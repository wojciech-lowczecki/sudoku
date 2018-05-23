package pl.plh.app.sudoku.common;

public final class SudokuParameters {
    public static final char DEFAULT_EMPTY_SYMBOL = '.';

    // Prameters should be so adjusted that the VALUE is in the range 4-35.
    // (4x4 is the smallest possible sudoku size and 35 is a quantity of 
    //  possible digits 1-9,a-z)
    public static int BOX_ROWS = 3;
    public static int BOX_COLUMNS = 3;
    public static final int MIN_VALUE = 1;

    public static final int VALUES = BOX_ROWS * BOX_COLUMNS;
    public static final int MAX_VALUE = MIN_VALUE + VALUES - 1;
    public static final int ELEMENTS = VALUES * VALUES;

    private SudokuParameters() {
    }
}
