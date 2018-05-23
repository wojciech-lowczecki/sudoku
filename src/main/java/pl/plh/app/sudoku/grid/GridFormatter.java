package pl.plh.app.sudoku.grid;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

public abstract class GridFormatter {
    public static final GridFormatter DEFAULT_FORMATTER = new GridFormatter() {
        private final char emptySymbol = DEFAULT_EMPTY_SYMBOL;

        @Override
        public String format(Grid grid) {
            checkNotNull(grid);
            StringBuilder buff = new StringBuilder();
            for (int r = 0; r < VALUES; r++) {
                for (int c = 0; c < VALUES; c++) {
                    Cell cell = grid.getCell(r, c);
                    buff.append(cell.isSettled() ? Character.forDigit(cell.getValue(), MAX_VALUE + 1) : emptySymbol);
                    if (c == VALUES - 1) {
                        break;
                    }
                    buff.append('\u0020');
                }
                if (r == VALUES - 1) {
                    break;
                }
                buff.append('\n');
            }
            return buff.toString();
        }
    };

    public abstract String format(Grid grid);
}
