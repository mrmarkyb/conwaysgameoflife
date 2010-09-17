package mburnett.gol;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameTests {

    @Test
    public void lifecycleASingleLiveCellKillsIt() {
        Game game = new Game(5, 5);
        game.spawn(2, 2);
        game.lifecycle();
        assertThat(game, equalTo(new Game(5,5)));
    }

    @Test
    public void blinkerWorks() {

        Game game = verticalBlinkerGame();
        game.lifecycle();
        assertThat(game, equalTo(horizontalBlinkerGame()));
        game.lifecycle();
        assertThat(game, equalTo(verticalBlinkerGame()));
    }

    @Test
    public void BlockWorksAcrossGridBoundary() {
        Game game = blockAcrossBoundariesGame();
        game.lifecycle();
        assertThat(game, equalTo(blockAcrossBoundariesGame()));
    }

    private Game horizontalBlinkerGame() {
        Game game = new Game(5, 5);
        game.spawn(1, 2);
        game.spawn(2, 2);
        game.spawn(3, 2);
        return game;
    }

    private Game verticalBlinkerGame() {
        Game game = new Game(5, 5);
        game.spawn(2, 1);
        game.spawn(2, 2);
        game.spawn(2, 3);
        return game;
    }

    private Game blockAcrossBoundariesGame() {
        Game game = new Game(5, 5);
        game.spawn(0, 0);
        game.spawn(0, 4);
        game.spawn(4, 0);
        game.spawn(4, 4);
        return game;
    }

}
