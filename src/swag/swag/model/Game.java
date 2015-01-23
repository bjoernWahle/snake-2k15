package swag.swag.model;

import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class Game {
    private World world;

    public Game(int worldSizeX, int worldSizeY, int snakeStartLength, Position snakeStartPosition, Direction snakeStartDirection) {
        world = new World(worldSizeX, worldSizeY);
        world.setSnake(snakeStartLength, snakeStartPosition, snakeStartDirection);
    }

    public World getWorld() {
        return world;
    }
}
