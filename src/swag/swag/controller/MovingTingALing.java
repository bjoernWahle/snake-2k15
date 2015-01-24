package swag.swag.controller;

import swag.swag.model.Geometry.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by flofincke on 24/01/15.
 */
public class MovingTingALing implements KeyListener {

    LordOfSwagaliciousness lord;

    public MovingTingALing(LordOfSwagaliciousness lord) {
        this.lord = lord;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getExtendedKeyCode())
        {
            case KeyEvent.VK_UP:
                lord.getGame().getWorld().getSnake().setCurrentMovementDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_DOWN:
                lord.getGame().getWorld().getSnake().setCurrentMovementDirection(Direction.TOP);
                break;
            case KeyEvent.VK_RIGHT:
                lord.getGame().getWorld().getSnake().setCurrentMovementDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                lord.getGame().getWorld().getSnake().setCurrentMovementDirection(Direction.LEFT);
                break;
            default:
               break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
