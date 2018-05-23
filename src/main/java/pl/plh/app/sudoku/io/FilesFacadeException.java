package pl.plh.app.sudoku.io;

public class FilesFacadeException extends RuntimeException {
    private FilesFacadeProblem problem;

    public enum FilesFacadeProblem {
        FAILED_READING("failed reading from a file"),
        UNABLE_WRITING("unable to write to a file"),
        UNABLE_READING("unable to read file"),
        UNABLE_PREPARE("unable to prepare writing to a file"),
        BAD_FILE_NAME("bad file name");

        private final String description;

        FilesFacadeProblem(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public FilesFacadeException(FilesFacadeProblem problem, Exception cause) {
        super(String.format("%1$s\n%2$s: %3$s",
                problem.description, cause.getClass().getSimpleName(), cause.getMessage()));
        this.problem = problem;
    }

    public FilesFacadeProblem getProblem() {
        return problem;
    }
}
