package pl.plh.app.sudoku.common;

public class CommonValidator {
    public static <T> void checkNotNull(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null " + t.getClass().getSimpleName());
        }
    }

    public static void checkNotBlank(String s, String message) {
        if (s == null || s.trim().isEmpty()) {
            if (message == null) {
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkRange(int n, int min, int max) {
        if (n < min || max < n) {
            throw new IllegalArgumentException(
                    String.format("value %1$d is out of the range %2$d - %3$d", n, min, max));
        }
    }
}
