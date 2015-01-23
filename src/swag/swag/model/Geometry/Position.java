package swag.swag.model.Geometry;

/**
 * Created by BJOERN on 23.01.2015.

 */
public class Position {
    private final int x;
    private final int y;

    public Position(int x,int y) {
        this.x = x; this.y = y;
    }

    /**
     * returns the direction of this position to another position
     * since we aren't interested in topright and similiar, we just return right / left for topright downright / topleft, downleft
     * @param otherPos position to be compared
     * @return null if positions are equal
     */
    public Direction directionTo(Position otherPos) {
        if(otherPos.getX() > this.getX()) {
            return Direction.LEFT;
        } else if(otherPos.getX() < this.getX()) {
            return Direction.RIGHT;
        } else if(otherPos.getY() > this.getY()) {
            return Direction.DOWN;
        } else if(otherPos.getY() < this.getY()) {
            return Direction.TOP;
        } else {
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
