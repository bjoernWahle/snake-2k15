package swag.swag.model;

import com.sun.javafx.geom.BoxBounds;
import swag.swag.model.Geometry.Position;
import swag.swag.model.Geometry.Direction;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class Snake {

    private int length;

    private Position headPosition;

    private Deque<Position> tailPositionList;

    private Direction currentMovementDirection;

    private boolean consumedGoodie = false;

    private World world;

    public Snake (int length, Position startPosition, Direction startDirection, World world) {
        if(length < 0) {
            throw new IllegalArgumentException("Lenght has to be at least 1, but is " + length);
        }
        this.world = world;
        this.length = length;
        this.currentMovementDirection = startDirection;
        this.headPosition = startPosition;
        this.tailPositionList = new LinkedList<Position>();

        for(int i = 1; i <= length; i++) {
            switch (currentMovementDirection.invert()) {
                case TOP:
                    tailPositionList.add(new Position(startPosition.getX(), (startPosition.getY()+i) % world.getSizeY()));
                    break;
                case DOWN:
                    tailPositionList.add(new Position(startPosition.getX(), (startPosition.getY()-i) % world.getSizeY()));
                    break;
                case LEFT:
                    tailPositionList.add(new Position((startPosition.getX()-i) % world.getSizeX(), startPosition.getY()));
                    break;
                case RIGHT:
                    tailPositionList.add(new Position((startPosition.getX()+i) % world.getSizeX(), startPosition.getY()));
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String s =  "{Snake: {Head: ("+headPosition.getX() +"," +headPosition.getY() +"), Tail: [";
        for(Position tailPosition : tailPositionList) {
            s += "("+tailPosition.getX()+","+tailPosition.getY()+ ")";
        }
        s += "]} Current direction: " + currentMovementDirection + " }";
        return s;
    }

    public boolean checkSelfCollision() {
        return tailPositionList.contains(headPosition);
    }

    public Position getHeadPosition() {
        return headPosition;
    }

    public void setConsumedGoodie(boolean consumedGoodie) {
        this.consumedGoodie = consumedGoodie;
    }

    public boolean getConsumedGoodie() {
        return this.consumedGoodie;
    }

    public void enlargen() {
        this.setConsumedGoodie(true);
    }

    public void move() {
        this.tailPositionList.addFirst(this.headPosition);
        switch (currentMovementDirection) {
            case TOP:
                this.headPosition = new Position(headPosition.getX(), (headPosition.getY() + 1) % world.getSizeY());
                break;
            case DOWN:
                this.headPosition = new Position(headPosition.getX(), (headPosition.getY() - 1) % world.getSizeY());
                break;
            case LEFT:
                this.headPosition = new Position((headPosition.getX() -1) % world.getSizeX(), headPosition.getY());
                break;
            case RIGHT:
                this.headPosition = new Position((headPosition.getX() +1) % world.getSizeX(), headPosition.getY());
                break;
        }
        if(consumedGoodie) {
           setConsumedGoodie(false);
        } else {
            this.tailPositionList.removeLast();
        }
    }

    public Deque<Position> getTailList() {
        return tailPositionList;
    }
}
