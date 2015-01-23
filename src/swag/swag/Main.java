package swag.swag;

import swag.swag.model.Game;
import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;

public class Main {

    public static void main(String[] args) {
	    Game game = new Game(100, 100, 5, new Position(5, 5), Direction.RIGHT);
        System.out.println(game.getWorld().getSnake());
    }
}
