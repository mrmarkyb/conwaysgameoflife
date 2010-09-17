package mburnett.gol;

import java.util.Iterator;

public class GridNavigation implements Iterable<GridPosition>{
    private GridAxis xAxis;
    private GridAxis yAxis;

    public GridNavigation(int rows, int columns) {
        xAxis = new GridAxis(rows);
        yAxis = new GridAxis(columns);
    }

    public GridPosition getStartPosition() {
        return new GridPosition(0, 0);
    }

    public GridPosition moveUp(GridPosition position) {
        return new GridPosition(xAxis.decrement(position.row()), position.column());
    }

    public GridPosition moveLeft(GridPosition position) {
        return new GridPosition(position.row(), yAxis.decrement(position.column()));
    }

    public GridPosition moveRight(GridPosition position) {
        return new GridPosition(position.row(), yAxis.increment(position.column()));
    }

    public GridPosition next(GridPosition position) {
        GridPosition newPosition = moveRight(position);
        if (newPosition.column() == 0) {
            newPosition = this.moveDown(newPosition);
        }
        return newPosition;
    }

    public GridPosition moveDown(GridPosition position) {
        return new GridPosition(xAxis.increment(position.row()), position.column());
    }

    GridPosition getEndPosition() {
        return moveUp(moveLeft(getStartPosition()));
    }

    public Iterator<GridPosition> iterator() {
        return new Iterator<GridPosition>() {

            GridPosition position = getStartPosition();
            public boolean hasNext = true;

            public boolean hasNext() {
                return hasNext;
            }

            public GridPosition next() {
                GridPosition currentPosition = position;
                position = GridNavigation.this.next(position);
                if(position.equals(getStartPosition())) {
                    hasNext = false;
                }
                return currentPosition;
            }

            public void remove() {
                throw new UnsupportedOperationException("no way");
            }
        };
    }
}
