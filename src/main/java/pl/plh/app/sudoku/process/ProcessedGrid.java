package pl.plh.app.sudoku.process;

import pl.plh.app.sudoku.grid.Grid;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.CommonValidator.checkRange;
import static pl.plh.app.sudoku.common.SudokuParameters.ELEMENTS;

class ProcessedGrid {
    private final Grid grid;
    private int settled;

    ProcessedGrid(Grid grid, int settled) {
        checkNotNull(grid);
        checkRange(settled , 0, ELEMENTS);
        this.grid = grid;
        this.settled = settled;
    }

    Grid getGrid() {
        return grid;
    }

    int settled() {
        return settled;
    }

    void settledIncr() {
        settled++;
    }

    ProcessedGrid deepCopy() {
        return new ProcessedGrid(grid.deepCopy(), settled);
    }
}
