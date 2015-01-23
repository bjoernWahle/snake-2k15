package swag.swag.model.Geometry;

/**
 * Created by BJOERN on 23.01.2015.
 */
public enum Direction {

    TOP, DOWN, LEFT, RIGHT;

    public Direction invert() {
        switch (this) {
            case TOP:
                return DOWN;
            case DOWN:
                return TOP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new IllegalArgumentException("Can't happen. Stupid IDE.");
        }
    }

}
