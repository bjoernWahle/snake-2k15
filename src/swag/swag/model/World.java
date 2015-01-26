package swag.swag.model;

import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;

import java.util.*;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class World extends Observable {
    /**
     * width of the world
     */
    private final int sizeX;

    /**
     * height of the world
     */
    private final int sizeY;

    private Snake snake;

    private Set<Position> goodiePositionSet;

    private Random random;

    public World(int sizeX, int sizeY) {
        this.random = new Random();
        if(sizeX <= 0) {
            throw new IllegalArgumentException("Height of the field has to be greater than 0, but is " + sizeY);
        }
        if(sizeX <= 0) {
            throw new IllegalArgumentException("Width of the field has to be greater than 0, but is " + sizeX);
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.goodiePositionSet = new HashSet<Position>();
        this.generateGoodie();
        this.generateGoodie();
        this.generateGoodie();
        this.generateGoodie();
        this.generateGoodie();
        this.generateGoodie();
    }

    public void move() {
        snake.move();
        setChanged();
        notifyObservers();
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSnake(int snakeLength, Position snakeStartPosition, Direction snakeStartDirection) {
        this.snake = new Snake(snakeLength, snakeStartPosition, snakeStartDirection, this);
    }

    public Snake getSnake() {
        return snake;
    }

    public void checkIfGoodieWasComsumed() {
        if(goodiePositionSet.contains(snake.getHeadPosition())) {
            snake.enlargen();
            // remove goodie
            goodiePositionSet.remove(snake.getHeadPosition());

            // generate new goodie
            this.generateGoodie();
        }
    }

    private void generateGoodieAt(Position position) {
        goodiePositionSet.add(position);
    }

    public void generateGoodie() {
        Position newGoodie = new Position(random.nextInt(getSizeX()), random.nextInt(getSizeY()));
        while((snake != null && snake.getTailList().contains(newGoodie)) || goodiePositionSet.contains(newGoodie)) {
            newGoodie = new Position(random.nextInt(getSizeX()), random.nextInt(getSizeY()));
        }
        goodiePositionSet.add(newGoodie);
    }

    public Set getGoodieSet() {
        return this.goodiePositionSet;
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < getSizeY(); i++ ) {
            for(int j = 0; j < getSizeX(); j++ ) {
                Position pos = new Position(j, i);
                if(getSnake().getHeadPosition().equals(pos) || getSnake().getTailList().contains(pos) || goodiePositionSet.contains(pos)) {
                    s += "x";
                } else {
                    s += "o";
                }
            }
            s += "\n";
        }
        return s;
    }
}
