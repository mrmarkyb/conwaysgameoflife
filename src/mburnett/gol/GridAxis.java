package mburnett.gol;

public class GridAxis {
    private int extent;

    public GridAxis(int extent) {
        this.extent = extent;
    }

    public int decrement(int position) {
        int decremented = position -1;
        if(decremented<0) {
            decremented = extent -1;
        }
        return decremented;
    }

    public int increment(int position) {
        int incremented = position + 1;
        if (incremented >= extent) {
            incremented = 0;
        }
        return incremented;
    }

    public int extent() {
        return extent;
    }
}
