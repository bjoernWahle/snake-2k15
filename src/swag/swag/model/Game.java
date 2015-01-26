package swag.swag.model;

import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class Game {
    private World world;
    private GameState gameState;
    private World startWorld;

    public Game(int worldSizeX, int worldSizeY, int snakeStartLength, Position snakeStartPosition, Direction snakeStartDirection) {
        world = new World(worldSizeX, worldSizeY);
        world.setSnake(snakeStartLength, snakeStartPosition, snakeStartDirection);
        startWorld = new World(worldSizeX, worldSizeY);
        startWorld.setSnake(snakeStartLength, snakeStartPosition, snakeStartDirection);
        gameState = GameState.NOT_STARTED;
    }

    public World getWorld() {
        return world;
    }

    public void startGame() {
        if(gameState != GameState.NOT_STARTED) {
            throw new IllegalStateException("Game can't be started from an other gamestate than " + GameState.NOT_STARTED);
        } else {
            gameState = GameState.RUNNING;
        }
    }

    public void endGame() {
        if(gameState != GameState.RUNNING) {
            throw new IllegalStateException("Game can't be ended from an other gamestate than " + GameState.RUNNING);
        } else {
            gameState = GameState.ENDED;
        }
    }

    public void resetGame() {
        if(gameState != GameState.ENDED) {
            throw new IllegalStateException("Game can't be resetted from an other gamestate than " + GameState.ENDED);
        } else {
            gameState = GameState.NOT_STARTED;
        }
    }

    public void advanceToNextRound() {
        if(gameState != GameState.RUNNING) {
            throw new IllegalStateException("Game is not running. Current gamestate is " + gameState);
        }
        if(this.checkGameEndingConditions()) {
            this.endGame();
        } else {
            world.checkIfGoodieWasComsumed();
            world.move();
        }
    }

    private boolean checkGameEndingConditions() {
        // if snake is colliding with itself, game should be ended
        return world.getSnake().checkSelfCollision();
    }

    public GameState getGameState() {
        return gameState;
    }
}
