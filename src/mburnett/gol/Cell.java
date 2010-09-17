package mburnett.gol;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private boolean alive;
    private Set<Cell> neighbours = new HashSet<Cell>();

    public Cell(boolean isAlive) {
        alive = isAlive;
    }

    public Cell() {
        this(false);
    }

    public boolean isAlive() {
        return alive;
    }

    public void spawn() {
        alive = true;
    }

    public Cell nextGeneration() {
        return new Cell(shouldLive(getLiveNeighbourCount()));
    }

    private boolean shouldLive(int liveNeighbourCount) {
        if (alive) {
            return liveNeighbourCount > 1 && liveNeighbourCount < 4;
        } else {
            return liveNeighbourCount == 3;
        }
    }

    private int getLiveNeighbourCount() {
        int liveNeighbourCount = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.isAlive()) {
                liveNeighbourCount++;
            }
        }
        return liveNeighbourCount;
    }

    public void addNeighbour(Cell cell) {
        this.neighbours.add(cell);
        cell.neighbours.add(this);
    }

    public int neighbourCount() {
        return neighbours.size();
    }


    @Override
    public String toString() {
        return "mburnett.gol.Cell{" +
                "alive=" + alive +
                '}';
    }
}
