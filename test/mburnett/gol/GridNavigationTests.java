package mburnett.gol;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GridNavigationTests {
    private GridNavigation navigation = new GridNavigation(3,3);


    @Test
    public void getsStartingGridPosition() {
        GridPosition position = navigation.getStartPosition();
        assertThat(position, equalTo(new GridPosition(0,0)));
    }

    @Test
    public void movesUp() {
        GridPosition up = navigation.moveUp(new GridPosition(1,1));
        assertThat(up, equalTo(new GridPosition(0,1)));
    }

    @Test
    public void movesUpOverBoundary() {
        GridPosition up = navigation.moveUp(new GridPosition(0,1));
        assertThat(up, equalTo(new GridPosition(2,1)));
    }

    @Test
    public void movesLeft() {
        GridPosition left = navigation.moveLeft(new GridPosition(1,1));
        assertThat(left, equalTo(new GridPosition(1,0)));
    }

    @Test
    public void movesLeftOverBoundary() {
        GridPosition left = navigation.moveLeft(new GridPosition(0,0));
        assertThat(left, equalTo(new GridPosition(0,2)));
    }

   @Test
    public void movesRight() {
        GridPosition right = navigation.moveRight(new GridPosition(1,1));
        assertThat(right, equalTo(new GridPosition(1,2)));
    }
    @Test
    public void movesRightOverBoundary() {
        GridPosition right = navigation.moveRight(new GridPosition(1,2));
        assertThat(right, equalTo(new GridPosition(1,0)));
    }

    @Test
    public void getsNextPosition() {
        GridPosition next = navigation.next(new GridPosition(1,1));
        assertThat(next, equalTo(new GridPosition(1,2)));
    }

    @Test
    public void getsNextPositionOverBoundary() {
        GridPosition next = navigation.next(new GridPosition(1,2));
        assertThat(next, equalTo(new GridPosition(2,0)));
    }
}
