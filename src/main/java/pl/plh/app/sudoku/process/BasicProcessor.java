package pl.plh.app.sudoku.process;

import pl.plh.app.sudoku.grid.*;

import java.util.*;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

public class BasicProcessor implements Processor {
    private enum AdjustState {
        DEADLOCK, REDUCED, SETTLED, NOT_CHANGED
    }

    @Override
    public Grid process(final Grid grid) {
        checkNotNull(grid);

        OptionalInt examineResult = new GridExaminer().examine(grid);
        if (!examineResult.isPresent()) {
            return null;
        }

        Grid gridCopy = grid.deepCopy();
        int settled = examineResult.getAsInt();
        if(settled == ELEMENTS) {
            return gridCopy;
        }

        ProcessedGrid result = attempt(new ProcessedGrid(gridCopy, settled));
        return result == null ? null : result.getGrid();
    }

    private ProcessedGrid attempt(final ProcessedGrid processedGrid) {
        ProcessedGrid eliminateResult = eliminate(processedGrid);
        if (eliminateResult != null) {
            if (eliminateResult.settled() == ELEMENTS) {
                return eliminateResult;
            }
            Candidates cands = new Candidates(eliminateResult);
            while(cands.hasNext()){
                ProcessedGrid attemptResult = attempt(cands.next());
                if (attemptResult != null) {
                   return attemptResult;
                }
            }
        }
        return null;
    }

    private ProcessedGrid eliminate(final ProcessedGrid processedGrid) {
        Grid grid = processedGrid.getGrid();
        boolean changed;
        do {
            changed = false;
            for (int r = 0; r < VALUES; r++) {
                for (int c = 0; c < VALUES ; c++) {
                    if (grid.getCell(r, c).isSettled()) {
                        continue;
                    }
                    switch(adjust(grid, r, c)) {
                        case NOT_CHANGED:
                            continue;
                        case DEADLOCK:
                            return null;
                        case SETTLED:
                            processedGrid.settledIncr();
                        case REDUCED:
                            changed = true;
                            break;
                        default:
                            throw new IllegalStateException("bad adjust status");
                    }
                }
            }
        } while (changed);

        return processedGrid;
    }

    private AdjustState adjust(Grid grid, int row, int column) {
        Cell cell = grid.getCell(row, column);
        Set<Integer> possibles = cell.getPossibles();
        int initSize = possibles.size();
        for (int v : new ArrayList<>(possibles)) {
            int currentSize = possibles.size();
            if (findInRow(v, grid, row) || findInColumn(v, grid, column) || findInBox(v, grid, row, column)) {
                if (currentSize == 1) {
                    return AdjustState.DEADLOCK;
                }
                cell.setImpossible(v);
            } else if (currentSize == 1) {
                cell.setValue(v);
                return AdjustState.SETTLED;
            }
        }
        if (possibles.size() == initSize) {
            return AdjustState.NOT_CHANGED;
        }
        return AdjustState.REDUCED;
    }

    private boolean findInRow(int value, Grid grid, int row) {
        for (int c = 0; c < VALUES; c++) {
            Cell cell = grid.getCell(row, c);
            if (cell.isSettled() && cell.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean findInColumn(int value, Grid grid, int column) {
        for (int r = 0; r < VALUES; r++) {
            Cell cell = grid.getCell(r, column);
            if (cell.isSettled() && cell.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean findInBox(int value, Grid grid, int row, int column) {
        final int firstRow = (row / BOX_ROWS) * BOX_ROWS;
        final int firstColumn = (column / BOX_COLUMNS) * BOX_COLUMNS;
        final int rowBounds = firstRow + BOX_ROWS;
        final int columnBounds = firstColumn + BOX_COLUMNS;

        for (int r = firstRow; r < rowBounds; r++) {
            for (int c = firstColumn; c < columnBounds; c++) {
                Cell cell = grid.getCell(r, c);
                if (cell.isSettled() && cell.getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
