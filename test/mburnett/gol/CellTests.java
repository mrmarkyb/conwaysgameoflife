package mburnett.gol;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CellTests {
    @Test
    public void cellIsDead() {
        Cell cell = new Cell(false);
        assertFalse(cell.isAlive());
    }

    @Test
    public void cellComesAlive() {
        Cell cell = new Cell(false);
        cell.spawn();
        assertTrue(cell.isAlive());
    }

    @Test
    public void cellDiesWhenLessThanTwoLiveNeighbours() {
        Cell cell = new Cell(true);
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(false));
        assertFalse(cell.nextGeneration().isAlive());
    }

    @Test
    public void cellStaysAliveWhenTwoLiveNeighbours() {
        Cell cell = new Cell(true);
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        assertTrue(cell.nextGeneration().isAlive());
    }

    @Test
    public void cellDiesWhenMoreThanThreeNeighbours() {
        Cell cell = new Cell(true);
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        assertFalse(cell.nextGeneration().isAlive());
    }

    @Test
    public void cellComeAliveWhenThreeNeighbours() {
        Cell cell = new Cell(false);
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(false));
        assertTrue(cell.nextGeneration().isAlive());
    }

    @Test
    public void cellStaysDeadWhenOnlyTwoLiveNeighbours() {
        Cell cell = new Cell(false);
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(true));
        cell.addNeighbour(new Cell(false));
        cell.addNeighbour(new Cell(false));
        assertFalse(cell.nextGeneration().isAlive());
    }

    @Test
    public void reciprocatesNeighbours() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        cell1.addNeighbour(cell2);
        assertEquals(cell2.neighbourCount(), 1);

    }


}
