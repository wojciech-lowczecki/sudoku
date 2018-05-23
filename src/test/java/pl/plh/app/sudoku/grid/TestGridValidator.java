package pl.plh.app.sudoku.grid;

import org.junit.Test;

import java.util.OptionalInt;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGridValidator {
    @Test
    public void testValidateAbsoluteEmpty() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN
        OptionalInt result = examiner.examine(grid);

        //THEN
        assertTrue(result.isPresent());
        assertEquals(0, result.getAsInt());
    }

    @Test
    public void testValidateUnresolved() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,2,0,3,8,0,1,
                        0,0,0,7,0,6,0,5,2,
                        2,0,0,0,0,0,0,7,9,
                        0,2,0,1,5,7,9,3,4,
                        0,0,3,0,0,0,1,0,0,
                        9,1,7,3,8,4,0,2,0,
                        1,8,0,0,0,0,0,0,6,
                        7,3,0,6,0,1,0,0,0,
                        6,0,5,8,0,9,0,0,0
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN
        OptionalInt result = examiner.examine(grid);

        //THEN
        assertTrue(result.isPresent());
        assertEquals(38, result.getAsInt());
    }

    @Test
    public void testValidateResolved() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        4,2,6,5,7,1,3,9,8,
                        8,5,7,2,9,3,1,4,6,
                        1,3,9,4,6,8,2,7,5,
                        9,7,1,3,8,5,6,2,4,
                        5,4,3,7,2,6,8,1,9,
                        6,8,2,1,4,9,7,5,3,
                        7,9,4,6,3,2,5,8,1,
                        2,6,5,8,1,4,9,3,7,
                        3,1,8,9,5,7,4,6,2
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN
        OptionalInt result = examiner.examine(grid);

        //THEN
        assertTrue(result.isPresent());
        assertEquals(81, result.getAsInt());
    }

    @Test
    public void testValidateInvalidInRowCase1() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        1,2,3,4,5,6,3,8,9
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN & THEN
        assertFalse(examiner.examine(grid).isPresent());
    }

    @Test
    public void testValidateInvalidInRowCase2() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        1,2,3,4,5,6,7,8,8
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN & THEN
        assertFalse(examiner.examine(grid).isPresent());
    }

    @Test
    public void testValidateInvalidInColumnCase1() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,1,
                        0,0,0,0,0,0,0,0,2,
                        0,0,0,0,0,0,0,0,3,
                        0,0,0,0,0,0,0,0,4,
                        0,0,0,0,0,0,0,0,5,
                        0,0,0,0,0,0,0,0,6,
                        0,0,0,0,0,0,0,0,3,
                        0,0,0,0,0,0,0,0,8,
                        0,0,0,0,0,0,0,0,9
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN & THEN
        assertFalse(examiner.examine(grid).isPresent());
    }

    @Test
    public void testValidateInvalidInColumnCase2() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,1,
                        0,0,0,0,0,0,0,0,2,
                        0,0,0,0,0,0,0,0,3,
                        0,0,0,0,0,0,0,0,4,
                        0,0,0,0,0,0,0,0,5,
                        0,0,0,0,0,0,0,0,6,
                        0,0,0,0,0,0,0,0,7,
                        0,0,0,0,0,0,0,0,8,
                        0,0,0,0,0,0,0,0,8
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN & THEN
        assertFalse(examiner.examine(grid).isPresent());
    }

    @Test
    public void testValidateInvalidInBox() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,1,2,3,0,0,0,
                        0,0,0,4,5,6,0,0,0,
                        0,0,0,3,8,9,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0
                ).build();
        GridExaminer examiner = new GridExaminer();

        //WHEN & THEN
        assertFalse(examiner.examine(grid).isPresent());
    }
}
