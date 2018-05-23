package pl.plh.app.sudoku.process;

import pl.plh.app.sudoku.grid.Cell;

import java.util.ArrayDeque;
import java.util.Queue;

import static pl.plh.app.sudoku.common.SudokuParameters.VALUES;

class Candidates {
    private final ProcessedGrid origin;
    private Queue<Integer> possibles;
    private int row;
    private int column;

    Candidates(final ProcessedGrid origin) {
        this.origin = origin;
        selectFirstCellWithMinPossibles();
    }

    boolean hasNext() {
        return !possibles.isEmpty();
    }

    ProcessedGrid next() {
        ProcessedGrid copy = origin.deepCopy();
        copy.getGrid().getCell(row, column).setValue(possibles.remove());
        copy.settledIncr();
        return copy;
    }

    private void selectFirstCellWithMinPossibles() {
        int minSize = VALUES + 1;
        for(int r = 0; r < VALUES; r++){
            for(int c = 0; c < VALUES; c++){
                Cell cell = origin.getGrid().getCell(r, c);
                if(!cell.isSettled()) {
                    int sz = cell.getPossibles().size();
                    if (sz < minSize) {
                        minSize = sz;
                        row = r;
                        column = c;
                    }
                }
            }
        }
        if (minSize == VALUES + 1) {
            throw new IllegalStateException("full grid");
        }

        possibles = new ArrayDeque<>(origin.getGrid().getCell(row, column).getPossibles());
    }
}