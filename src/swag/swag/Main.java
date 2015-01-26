package swag.swag;

import swag.swag.controller.LordOfSwagaliciousness;
import swag.swag.model.Game;
import swag.swag.model.Geometry.Direction;
import swag.swag.model.Geometry.Position;
import swag.swag.view.Display;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        });

    }

    public static void startGame() {
        Game game = new Game(47, 32, 20, new Position(30, 5), Direction.RIGHT);
        LordOfSwagaliciousness lord = new LordOfSwagaliciousness(game);
    }
}
