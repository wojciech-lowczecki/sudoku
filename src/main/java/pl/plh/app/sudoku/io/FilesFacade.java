package pl.plh.app.sudoku.io;

import pl.plh.app.sudoku.grid.*;

import static pl.plh.app.sudoku.grid.GridFormatter.DEFAULT_FORMATTER;
import static pl.plh.app.sudoku.grid.GridParser.DEFAULT_PARSER;

public interface FilesFacade {
    String absolutePath(String file);

    String readFileToString(String file);

    void writeStringToFile(String file, String s);

    boolean alreadyExists(String file);

    default Grid readGrid(String file) {
        try {
            return DEFAULT_PARSER.parse(readFileToString(file));
        } catch (GridException e) {
            throw new FilesFacadeException(FilesFacadeException.FilesFacadeProblem.FAILED_READING, e);
        }
    }

    default void writeGrid(String file, Grid grid) {
       writeStringToFile(file, DEFAULT_FORMATTER.format(grid));
    }
}
