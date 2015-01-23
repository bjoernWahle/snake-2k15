package swag.swag.model;

import swag.swag.model.Geometry.Position;
import swag.swag.model.Geometry.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJOERN on 23.01.2015.
 */
public class Snake {

    private int length;

    private Position headPosition;

    private List<Position> tailPositionList;

    private Direction currentMovementDirection;

    public Snake (int length, Position startPosition, Direction startDirection) {
        if(length < 0) {
            throw new IllegalArgumentException("Lenght has to be at least 1, but is " + length);
        }
        this.length = length;
        this.currentMovementDirection = startDirection;
        this.headPosition = startPosition;
        this.tailPositionList = new ArrayList<Position>();

        for(int i = 0; i < length; i++) {
            switch (currentMovementDirection.invert()) {
                case TOP:
                    tailPositionList.add(new Position(startPosition.getX(), startPosition.getY()+i));
                    break;
                case DOWN:
                    tailPositionList.add(new Position(startPosition.getX(), startPosition.getY()-i));
                    break;
                case LEFT:
                    tailPositionList.add(new Position(startPosition.getX()-i, startPosition.getY()));
                    break;
                case RIGHT:
                    tailPositionList.add(new Position(startPosition.getX()+i, startPosition.getY()));
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
}
