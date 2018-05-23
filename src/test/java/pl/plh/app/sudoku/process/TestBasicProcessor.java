package pl.plh.app.sudoku.process;

import org.junit.Test;
import pl.plh.app.sudoku.grid.Grid;
import pl.plh.app.sudoku.grid.GridExaminer;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestBasicProcessor {
    @Test
    public void testResolveNormal() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,4,8,3,2,0,0,
                        0,0,0,0,0,7,6,0,0,
                        9,7,0,0,0,0,0,0,0,
                        6,0,9,0,0,8,0,5,0,
                        1,0,0,0,0,0,0,0,8,
                        0,4,0,7,0,0,9,0,6,
                        0,0,0,0,0,0,0,9,3,
                        0,0,4,2,0,0,0,0,0,
                        0,0,1,9,3,5,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNotNull(resolved);
        assertEquals(81, new GridExaminer().examine(resolved).getAsInt());
    }

    @Test
    public void testResolveAlreadyResolved() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        5,1,6,4,8,3,2,7,9,
                        4,3,2,5,9,7,6,8,1,
                        9,7,8,1,6,2,5,3,4,
                        6,2,9,3,4,8,1,5,7,
                        1,5,7,6,2,9,3,4,8,
                        8,4,3,7,5,1,9,2,6,
                        2,6,5,8,1,4,7,9,3,
                        3,9,4,2,7,6,8,1,5,
                        7,8,1,9,3,5,4,6,2
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNotNull(resolved);
        assertEquals(81, new GridExaminer().examine(resolved).getAsInt());
    }

    @Test
    public void testResolveAbsoluteEmpty() {
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
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNotNull(resolved);
        assertEquals(81, new GridExaminer().examine(resolved).getAsInt());
    }

    @Test
    public void testResolveDeficient() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        0,0,0,0,0,0,0,0,0,
                        0,2,0,0,4,0,0,6,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,8,0,0,1,0,0,3,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,5,0,0,7,0,0,9,0,
                        0,0,0,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNotNull(resolved);
        assertEquals(81, new GridExaminer().examine(resolved).getAsInt());
    }

    @Test
    public void testResolveDeadlockInRowWithColumn() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,2,3,4,5,6,7,0,9,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,8,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveInvalid() {
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
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveDeadlockInRowWithBox() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,2,3,4,5,6,7,0,9,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,8,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveDeadlockInColumnWithRow() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,0,0,0,0,0,0,0,0,
                        2,0,0,0,0,0,0,0,0,
                        3,0,0,0,0,0,0,0,0,
                        4,0,0,0,0,0,0,0,0,
                        5,0,0,0,0,0,0,0,0,
                        6,0,0,0,0,0,0,0,0,
                        7,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,8,
                        9,0,0,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveDeadlockInColumnWithBox() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,0,0,0,0,0,0,0,0,
                        2,0,0,0,0,0,0,0,0,
                        3,0,0,0,0,0,0,0,0,
                        4,0,0,0,0,0,0,0,0,
                        5,0,0,0,0,0,0,0,0,
                        6,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        8,0,0,0,0,0,0,0,0,
                        9,0,7,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveDeadlockInBoxWithRow() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,2,3,0,0,0,0,0,0,
                        4,0,6,0,0,0,0,0,5,
                        7,8,9,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }

    @Test
    public void testResolveDeadlockInBoxWithColumn() {
        //GIVEN
        Grid grid = new Grid.GridBuilder()
                .addValues(
                        1,2,3,0,0,0,0,0,0,
                        4,0,6,0,0,0,0,0,0,
                        7,8,9,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,0,
                        0,5,0,0,0,0,0,0,0
                ).build();
        BasicProcessor processor = new BasicProcessor();

        //WHEN
        Grid resolved = processor.process(grid);

        //THEN
        assertNull(resolved);
    }
}
