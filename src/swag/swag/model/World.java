package swag.swag.model;

import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class World extends Observable {
    /**
     * width of the world
     */
    private int sizeX;

    /**
     * height of the world
     */
    private int sizeY;

    private Snake snake;

    private List<Goodie> goodiesList;

    public World(int sizeX, int sizeY) {
        if(sizeX <= 0) {
            throw new IllegalArgumentException("Height of the field has to be greater than 0, but is " + sizeY);
        }
        if(sizeX <= 0) {
            throw new IllegalArgumentException("Width of the field has to be greater than 0, but is " + sizeX);
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.goodiesList = new ArrayList<Goodie>();
    }

    public void setSnake(int snakeLength, Position snakeStartPosition, Direction snakeStartDirection) {
        this.snake = new Snake(snakeLength, snakeStartPosition, snakeStartDirection);
    }

    public Snake getSnake() {
        return snake;
    }
}
