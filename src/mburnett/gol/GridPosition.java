package mburnett.gol;

public class GridPosition {
    private int row;
    private int column;

    public GridPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridPosition that = (GridPosition) o;

        if (column != that.column) return false;
        if (row != that.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    public int row() {
        return row;
    }

    @Override
    public String toString() {
        return "mburnett.gol.GridPosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public int column() {
        return column;
    }
}
