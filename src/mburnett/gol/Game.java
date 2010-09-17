package mburnett.gol;

public class Game {
    public Grid currentGeneration;
    private int rows;
    private int columns;

    public Game(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        currentGeneration = new Grid(rows,columns);
        populateCells(rows, columns);
    }

    private void populateCells(int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for(int column=0;column<columns;column++) {
                currentGeneration.add(new Cell(false));
            }
        }
    }

    public void spawn(int row, int column) {
        currentGeneration.get(row,column).spawn();
    }

    public void lifecycle() {
        Grid nextGeneration = new Grid(rows,columns);
        for(Cell cell: currentGeneration) {
            nextGeneration.add(cell.nextGeneration());
        }
        currentGeneration = nextGeneration;
    }

    public Boolean[][] getStates() {
        return currentGeneration.getStates();  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        if (columns != game.columns) return false;
        if (rows != game.rows) return false;
        return currentGeneration.equals(game.currentGeneration);
    }

    @Override
    public int hashCode() {
        int result = currentGeneration.hashCode();
        result = 31 * result + rows;
        result = 31 * result + columns;
        return result;
    }

    @Override
    public String toString() {
        return "mburnett.gol.Game{" +
                "currentGeneration=" + currentGeneration +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
