package pl.plh.app.sudoku.grid;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotNull;
import static pl.plh.app.sudoku.common.SudokuParameters.*;

public abstract class GridParser {
    public static final GridParser DEFAULT_PARSER = new GridParser() {
        private final char emptySymbol = DEFAULT_EMPTY_SYMBOL;

        @Override
        public Grid parse(String s) {
            checkNotNull(s);
            if (Character.digit(emptySymbol, MAX_VALUE+1) > 0) {
                throw new IllegalArgumentException("using non-zero digit as empty symbol");
            }

            final int length = s.length();
            // preliminary check
            if(length < VALUES) {
                throw new MissingElementException();
            }

            Grid.GridBuilder gb = new Grid.GridBuilder();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isWhitespace(ch)) {
                    continue;
                }
                if (ch == emptySymbol) {
                    gb.addEmpty();
                } else  {
                    int value = Character.digit(ch, MAX_VALUE+1);
                    if (value < MIN_VALUE) {
                        throw new BadCharacterException(ch);
                    }
                    gb.addValue(value);
                }
            }
            return gb.build();
        }
    };

    public abstract Grid parse(String s);
}
