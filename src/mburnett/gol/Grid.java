package mburnett.gol;

import java.util.Iterator;

public class Grid implements Iterable<Cell> {
    Cell[][] cells;

    private GridAxis xAxis;
    private GridAxis yAxis;
    GridNavigation navigation;


    public Grid(int rows, int columns) {
        navigation = new GridNavigation(rows, columns);
        xAxis = new GridAxis(rows);
        yAxis = new GridAxis(columns);
        cells = new Cell[rows][columns];
    }

    public void add(Cell cell) {
        if (isFull()) {
            throw new IllegalStateException("currentGeneration already full");
        }
        GridPosition nextEmptyPosition = findNextEmptyPosition();

        cells[nextEmptyPosition.row()][nextEmptyPosition.column()] = cell;

        if (isFull()) {
            setNeighbours();
        }


    }

    private void setNeighbours() {
        GridPosition position = navigation.getStartPosition();
        do {
            setNeighbours(position);
            position = navigation.next(position);
        } while (!position.equals(navigation.getStartPosition()));
    }

    private void setNeighbours(GridPosition position) {
        Cell cell = getCell(position);
        cell.addNeighbour(getCell(navigation.moveLeft(position)));
        cell.addNeighbour(getCell(navigation.moveUp(position)));
        cell.addNeighbour(getCell(navigation.moveLeft(navigation.moveUp(position))));
        cell.addNeighbour(getCell(navigation.moveRight(navigation.moveUp(position))));
    }

    public GridPosition findNextEmptyPosition() {
        GridPosition position = navigation.getStartPosition();
        while (hasCell(position)) {
            position = navigation.next(position);
        }
        return position;
    }

    private boolean hasCell(GridPosition position) {
        return getCell(position) != null;
    }

    public boolean isFull() {
        return hasCell(navigation.getEndPosition());
    }

    private Cell getCell(GridPosition position) {
        return cells[position.row()][position.column()];
    }

    public Cell get(int x, int y) {
        return getCell(new GridPosition(x, y));
    }

    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {

            Iterator<GridPosition> positionIterator = navigation.iterator();

            public boolean hasNext() {
                return positionIterator.hasNext();
            }

            public Cell next() {
                return getCell(positionIterator.next());
            }

            public void remove() {
                throw new UnsupportedOperationException("no way");
            }
        };
    }

    public Boolean[][] getStates() {
        Boolean[][] states = new Boolean[xAxis.extent()][yAxis.extent()];
        GridPosition position = navigation.getStartPosition();
        do {
            Cell cell = getCell(position);
            states[position.row()][position.column()] = cell.isAlive();
            position = navigation.next(position);
        } while (!position.equals(navigation.getStartPosition()));
        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        boolean answer = true;
        Grid otherGrid = (Grid) o;
        for (GridPosition position : navigation) {
            answer &= otherGrid.getCell(position).isAlive() == getCell(position).isAlive();
        }

        return answer;
    }

    @Override
    public int hashCode() {
        return cells.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("mburnett.gol.Grid{cells=");
        for (GridPosition position : navigation) {
            builder.append(getCell(position));
            builder.append(",");
        }
        builder.append("}");
        return builder.toString();
    }
}
