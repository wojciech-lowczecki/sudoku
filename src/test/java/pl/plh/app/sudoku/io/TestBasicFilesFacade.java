package pl.plh.app.sudoku.io;

import org.junit.Test;
import pl.plh.app.sudoku.grid.Cell;
import pl.plh.app.sudoku.grid.Grid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static pl.plh.app.sudoku.common.SudokuParameters.VALUES;

@SuppressWarnings("Duplicates")
public class TestBasicFilesFacade {
    @Test
    public void testWriteReadString() {
        //GIVEN
        String asciiText = " !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                           "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~" + "\n\r\t\f";
        FilesFacade io = new BasicFilesFacade();

        //WHEN
        String file = "test-file.txt";
        io.writeStringToFile(file, asciiText);
        String readText = io.readFileToString(file);

        //THEN
        assertEquals(asciiText, readText);

        // Cleanup
        try {
            Files.delete(Paths.get(file));
        } catch (IOException e) {
            System.err.println("Unable to delete file " + file);
        }
    }

    @Test
    public void testWriteGridResolved() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
            .addValues(4,2,6,5,7,1,3,9,8,
                       8,5,7,2,9,3,1,4,6,
                       1,3,9,4,6,8,2,7,5,
                       9,7,1,3,8,5,6,2,4,
                       5,4,3,7,2,6,8,1,9,
                       6,8,2,1,4,9,7,5,3,
                       7,9,4,6,3,2,5,8,1,
                       2,6,5,8,1,4,9,3,7,
                       3,1,8,9,5,7,4,6,2).build();
        FilesFacade io = new BasicFilesFacade();

        //WHEN
        String target = "test-target.txt";
        io.writeGrid(target, grid);

        //THEN
        String expectedContent = "4 2 6 5 7 1 3 9 8" + '\n' +
                                 "8 5 7 2 9 3 1 4 6" + '\n' +
                                 "1 3 9 4 6 8 2 7 5" + '\n' +
                                 "9 7 1 3 8 5 6 2 4" + '\n' +
                                 "5 4 3 7 2 6 8 1 9" + '\n' +
                                 "6 8 2 1 4 9 7 5 3" + '\n' +
                                 "7 9 4 6 3 2 5 8 1" + '\n' +
                                 "2 6 5 8 1 4 9 3 7" + '\n' +
                                 "3 1 8 9 5 7 4 6 2";
        assertEquals(expectedContent, io.readFileToString(target));

        // Cleanup
        try {
            Files.delete(Paths.get(target));
        } catch (IOException e) {
            System.err.println("Unable to delete file " + target);
        }
    }

    @Test
    public void testWriteGridUnresolved() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
            .addValues(0,2,0,5,0,1,0,9,0,
                       8,0,0,2,0,3,0,0,6,
                       0,3,0,0,6,0,0,7,0,
                       0,0,1,0,0,0,6,0,0,
                       5,4,0,0,0,0,0,1,9,
                       0,0,2,0,0,0,7,0,0,
                       0,9,0,0,3,0,0,8,0,
                       2,0,0,8,0,4,0,0,7,
                       0,1,0,9,0,7,0,6,0).build();
        FilesFacade io = new BasicFilesFacade();

        //WHEN
        String target = "test-target.txt";
        io.writeGrid(target, grid);

        //THEN
        String expectedContent = ". 2 . 5 . 1 . 9 ." + '\n' +
                                 "8 . . 2 . 3 . . 6" + '\n' +
                                 ". 3 . . 6 . . 7 ." + '\n' +
                                 ". . 1 . . . 6 . ." + '\n' +
                                 "5 4 . . . . . 1 9" + '\n' +
                                 ". . 2 . . . 7 . ." + '\n' +
                                 ". 9 . . 3 . . 8 ." + '\n' +
                                 "2 . . 8 . 4 . . 7" + '\n' +
                                 ". 1 . 9 . 7 . 6 .";
        assertEquals(expectedContent, io.readFileToString(target));

        // Cleanup
        try {
            Files.delete(Paths.get(target));
        } catch (IOException e) {
            System.err.println("Unable to delete file " + target);
        }
    }

    @Test
    public void testReadGrid() {
        //GIVEN
        String fileContent = ".2.5.1.9.8..2.3..6.3..6..7...1...6..54.....19..2...7...9..3..8.2..8.4..7.1.9.7.6.";
        FilesFacade io = new BasicFilesFacade();
        String source = "test-source.txt";
        io.writeStringToFile(source ,fileContent);

        //WHEN
        Grid readGrid = io.readGrid(source);

        //THEN
        Grid expectedGrid = new Grid.GridBuilder()
            .addValues(0,2,0,5,0,1,0,9,0,
                       8,0,0,2,0,3,0,0,6,
                       0,3,0,0,6,0,0,7,0,
                       0,0,1,0,0,0,6,0,0,
                       5,4,0,0,0,0,0,1,9,
                       0,0,2,0,0,0,7,0,0,
                       0,9,0,0,3,0,0,8,0,
                       2,0,0,8,0,4,0,0,7,
                       0,1,0,9,0,7,0,6,0).build();
        for (int r = 0; r < VALUES; r++) {
            for (int c = 0; c < VALUES; c++) {
                Cell expCell = expectedGrid.getCell(r, c);
                Cell readCell = readGrid.getCell(r, c);
                assertEquals("position indexes: " + r + "," + c, expCell, readCell);
            }
        }

        // Cleanup
        try {
            Files.delete(Paths.get(source));
        } catch (IOException e) {
            System.err.println("Unable to delete file " + source);
        }
    }

    @Test
    public void testReadGridWithWhiteSpaces() {
        //GIVEN
        String fileContent = "\n\r \t\f   .2.5. 1.9\n\r \t\f.8..2. 3..6.3.\n\r \t\f.6..7..  .1...6.\n\r  \t\f.5\n\r\t\f" +
                             "4...   ..19..2..\n\r  \t\f.7...9..3. .8.2..8.4. .7.1.\n\r\t  \f9.7. 6.\n\r \t\f  ";
        FilesFacade io = new BasicFilesFacade();
        String source = "test-source.txt";
        io.writeStringToFile(source ,fileContent);

        //WHEN
        Grid readGrid = io.readGrid(source);

        //THEN
        Grid expectedGrid = new Grid.GridBuilder()
            .addValues(0,2,0,5,0,1,0,9,0,
                       8,0,0,2,0,3,0,0,6,
                       0,3,0,0,6,0,0,7,0,
                       0,0,1,0,0,0,6,0,0,
                       5,4,0,0,0,0,0,1,9,
                       0,0,2,0,0,0,7,0,0,
                       0,9,0,0,3,0,0,8,0,
                       2,0,0,8,0,4,0,0,7,
                       0,1,0,9,0,7,0,6,0).build();
        for (int r = 0; r < VALUES; r++) {
            for (int c = 0; c < VALUES; c++) {
                Cell expCell = expectedGrid.getCell(r, c);
                Cell readCell = readGrid.getCell(r, c);
                assertEquals("position indexes: " + r + "," + c, expCell, readCell);
            }
        }

        // Cleanup
        try {
            Files.delete(Paths.get(source));
        } catch (IOException e) {
            System.err.println("Unable to delete file " + source);
        }
    }
}
