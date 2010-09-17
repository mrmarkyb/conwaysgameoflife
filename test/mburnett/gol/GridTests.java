package mburnett.gol;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GridTests {

    List<Cell> addedCells = new ArrayList<Cell>();


    @Test
    public void joinsUpTopLeftCell() {
        Grid grid = populateThreeByThreeGrid();
        assertThat(grid.get(0, 0).neighbourCount(), equalTo(8));
    }

    @Test
    public void JoinsUptTopMiddleCell() {
        Grid grid = populateThreeByThreeGrid();
        assertThat(grid.get(0, 1).neighbourCount(), equalTo(8));
    }

    @Test
    public void joinsUpMiddleCell() {
        Grid grid = populateThreeByThreeGrid();
        assertThat(grid.get(1, 1).neighbourCount(), equalTo(8));
    }

    @Test
    public void iterates() {
        Grid grid = populateThreeByThreeGrid();
        List<Cell> retrievedCells = new ArrayList<Cell>();
        for(Cell cell:grid) {
            retrievedCells.add(cell);
        }
        assertThat(retrievedCells, equalTo(addedCells));
    }

    @Test
    public void shouldDetectWhenFull() {
        assertTrue(populateThreeByThreeGrid().isFull());
    }

    private Grid populateThreeByThreeGrid() {
        Grid grid = new Grid(3, 3);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        addCell(grid);
        return grid;
    }

    private void addCell(Grid grid) {
        Cell cell = new Cell();
        grid.add(cell);
        addedCells.add(cell);

    }
}
