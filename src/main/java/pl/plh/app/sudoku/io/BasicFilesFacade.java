package pl.plh.app.sudoku.io;

import java.io.IOException;
import java.nio.file.*;

import static pl.plh.app.sudoku.common.CommonValidator.checkNotBlank;

public class BasicFilesFacade implements FilesFacade {
    private static final String MSG_BLANK_FILE = "blank file name";

    @Override
    public String absolutePath(String file) {
        checkNotBlank(file, MSG_BLANK_FILE);
        try {
            return Paths.get(file).toAbsolutePath().toString();
        } catch (InvalidPathException e) {
            throw new FilesFacadeException(FilesFacadeException.FilesFacadeProblem.BAD_FILE_NAME, e);
        }
    }

    @Override
    public String readFileToString(String file) {
        checkNotBlank(file, MSG_BLANK_FILE);
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException | SecurityException | InvalidPathException e) {
            throw new FilesFacadeException(FilesFacadeException.FilesFacadeProblem.UNABLE_READING, e);
        }
    }

    @Override
    public void writeStringToFile(String file, String s) {
        checkNotBlank(file, MSG_BLANK_FILE);
        try {
           Files.write(Paths.get(file), s.getBytes());
        } catch (IOException | SecurityException | InvalidPathException e) {
            throw new FilesFacadeException(FilesFacadeException.FilesFacadeProblem.UNABLE_WRITING, e);
        }
    }

    @Override
    public boolean alreadyExists(String file) {
        checkNotBlank(file, MSG_BLANK_FILE);
        try {
            return Files.isRegularFile(Paths.get(file));
        } catch (SecurityException | InvalidPathException e) {
            throw new FilesFacadeException(FilesFacadeException.FilesFacadeProblem.UNABLE_PREPARE, e);
        }
    }
}
