package pl.plh.app.sudoku.grid;

import java.util.OptionalInt;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

@SuppressWarnings("Duplicates")
public final class GridExaminer {
    private int counter = 0;

    public OptionalInt examine(Grid grid) {
        checkNotNull(grid);
        if (validateRowsCountingSettled(grid) && validateColumns(grid) && validateBoxes(grid)) {
            return OptionalInt.of(counter);
        }
        return OptionalInt.empty();
    }

    private boolean validateRowsCountingSettled(Grid grid) {
        for (int r = 0; r < VALUES; r++) {
            for (int c = 0; c < VALUES-1; c++) {
                Cell cell = grid.getCell(r, c);
                if (cell.isSettled()) {
                    counter++;
                    for (int i = c + 1; i < VALUES; i++) {
                        if (cell.equals(grid.getCell(r, i))) {
                            return false;
                        }
                    }
                }
            }
            if (grid.getCell(r, VALUES-1).isSettled()) {
                counter++;
            }
        }
        return true;
    }

    private boolean validateColumns(Grid grid) {
        for (int c = 0; c < VALUES; c++) {
            for (int r = 0; r < VALUES-1; r++) {
                Cell cell = grid.getCell(r, c);
                if (cell.isSettled()) {
                    for (int i = r + 1; i < VALUES; i++) {
                        if (cell.equals(grid.getCell(i, c))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean validateBoxes(Grid grid) {
        for (int r = 0; r < VALUES; r += BOX_ROWS) {
            for (int c = 0; c < VALUES; c += BOX_COLUMNS) {
                for (int i = 0; i < VALUES - 1; i++) {
                    Cell cell = grid.getCell(i / BOX_COLUMNS + r, i % BOX_COLUMNS + c);
                    if (cell.isSettled()) {
                        for (int j = i + 1; j < VALUES; j++) {
                            if (cell.equals(grid.getCell(j / BOX_COLUMNS + r, j % BOX_COLUMNS + c))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
