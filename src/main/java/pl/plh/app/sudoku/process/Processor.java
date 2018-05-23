package pl.plh.app.sudoku.process;

import pl.plh.app.sudoku.grid.Grid;

public interface Processor {
    Grid process(Grid grid);
}
