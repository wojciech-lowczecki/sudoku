package pl.plh.app.sudoku.grid;

import java.util.Arrays;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

public class Grid {
    private final Cell[][] cells;

    public static final class GridBuilder {
        private Cell[][] newCells = new Cell[VALUES][VALUES];

        private int fillCount = 0;

        public boolean filled() {
            return fillCount == ELEMENTS;
        }

        public GridBuilder addValue(int value) {
            return addCell(new Cell(value));
        }

        public GridBuilder addEmpty() {
            return addCell(new Cell());
        }

        public GridBuilder addValues(int... args) {
            if (args.length > ELEMENTS - fillCount) {
                throw new OverfilledGridException();
            }
            for (int i = 0; i < args.length; i++) {
                if(args[i] == 0) {
                    addEmpty();
                } else {
                    addValue(args[i]);
                }
            }
            return this;
        }

        public Grid build() {
            check();
            return new Grid(newCells);
        }

        private GridBuilder addCell(Cell cell) {
            checkNotNull(cell);
            if (filled()) {
                throw new OverfilledGridException();
            }
            newCells[fillCount/VALUES][fillCount%VALUES] = cell;
            fillCount++;
            return this;
        }

        private void check() {
            for (int r = 0; r < VALUES; r++) {
                for (int c = 0; c < VALUES; c++) {
                    if (newCells[r][c] == null) {
                        throw new MissingElementException(r, c);
                    }
                }
            }
        }
    }

    private Grid(Cell[][] cells) {
        this.cells = cells;
    }

    public  Grid deepCopy() {
        Cell[][] cellsCopy = new Cell[VALUES][VALUES];
        for (int r = 0; r < VALUES; r++) {
            for (int c = 0; c < VALUES; c++) {
                cellsCopy[r][c] = cells[r][c].deepCopy();
            }
        }
        return new Grid(cellsCopy);
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cells);
    }
}
