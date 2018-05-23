package pl.plh.app.sudoku.grid;

import java.util.*;

import static pl.plh.app.sudoku.common.CommonValidator.*;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

public final class Cell {
    private static final int EMPTY = 0;

    private static SortedSet<Integer> prepareNewPossibles(SortedSet<Integer> possibles) {
        if (possibles == null) {
            possibles = new TreeSet<>();
            for (int v = MIN_VALUE; v < MAX_VALUE + 1; v++) {
                possibles.add(v);
            }
            return possibles;
        }
        return new TreeSet<>(possibles);
    }

    private interface CellState {
        void setValue(int value);

        int getValue();

        boolean isSettled();

        void setImpossible(int value);

        SortedSet<Integer> getPossibles();
    }

    private final CellState settledState = new CellState() {
        private static final String EXC_MESSAGE = "cell is already settled";

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public void setValue(int value) {
            throw new IllegalStateException(EXC_MESSAGE);
        }

        @Override
        public boolean isSettled() {
            return true;
        }

        @Override
        public void setImpossible(int value) {
            throw new IllegalStateException(EXC_MESSAGE);
        }

        @Override
        public SortedSet<Integer> getPossibles() { throw new IllegalStateException(EXC_MESSAGE); }
    };

    private final CellState unsettledState = new CellState() {
        @Override
        public int getValue() {
            throw new NoSuchElementException("No value present");
        }

        @Override
        public void setValue(int value) {
            checkRange(value, MIN_VALUE, MAX_VALUE);
            if (!possibles.contains(value)) {
                throw new IllegalStateException(String.format("value %d is not possible", value));
            }
            Cell.this.value = value;
            possibles = null;
            state = settledState;
        }

        @Override
        public boolean isSettled() {
            return false;
        }

        @Override
        public void setImpossible(int value) {
            checkRange(value, MIN_VALUE, MAX_VALUE);
            if (!possibles.contains(value)) {
                throw new NoSuchElementException(String.format("value %d is already impossible", value));
            }
            possibles.remove(value);
        }

        @Override
        public SortedSet<Integer> getPossibles() {
            return Collections.unmodifiableSortedSet(possibles);
        }
    };

    private int value;
    private SortedSet<Integer> possibles;
    private CellState state;

    // creates unsettled cell with no possibles
    public Cell() {
        this(prepareNewPossibles(null));
    }

    // creates settled cell
    public Cell(int value) {
        checkRange(value, MIN_VALUE, MAX_VALUE);
        this.value = value;
        this.state = settledState;
    }

    // creates unsettled cell with given possibles
    private Cell(SortedSet<Integer> possibles) {
        checkNotNull(possibles);
        this.value = EMPTY;
        this.possibles = possibles;
        this.state = unsettledState;
    }

    public Cell deepCopy() {
        if (isSettled()) {
            return this;
        }
        return new Cell(prepareNewPossibles(possibles));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        state.setValue(value);
    }

    public boolean isSettled() {
        return state.isSettled();
    }

    public void setImpossible(int value) {
        state.setImpossible(value);
    }

    public SortedSet<Integer> getPossibles() {
        return state.getPossibles();
    }

    @Override
    public String toString() {
        return Integer.toString(value, VALUES+1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return value == cell.value;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
