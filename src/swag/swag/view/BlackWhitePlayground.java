package swag.swag.view;

import swag.swag.model.Geometry.Position;
import swag.swag.model.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by flofincke on 23/01/15.
 */
public class BlackWhitePlayground extends JComponent implements Observer {

    private int cellSize, offsetX, offsetY;
    private World world;

    public BlackWhitePlayground(int cellSize, World world) {
        this.cellSize = cellSize;
        this.world = world;
        this.offsetX = (280 / 2 - (world.getSizeX() * cellSize / 2)) + 102;
        this.offsetY = (200 / 2 - (world.getSizeY() * cellSize / 2)) + 280;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
        repaint();
    }

    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D g2d = (Graphics2D) graphic;

        //Image: 280x200
        //x= 105 y = 278

        /*g2d.fillRect(offsetX, offsetY, cellSize * world.getSizeX(), cellSize * world.getSizeY());
        g2d.setColor(Color.gray);
        int i;
        for (i = 1; i < worldDim.width; i++) {
            g2d.drawLine(i * cellSize - 1 + offsetX, 278, i * cellSize - 1 + offsetX, cellSize * worldDim.height - 1 + offsetY);
        }
        g2d.drawLine(i * cellSize - 1 + offsetX, 278, i * cellSize - 1 + offsetX, cellSize * worldDim.height - 1 + offsetY);
        for (int j = 0; j < worldDim.height + 1; j++) {
            g2d.drawLine(100, j * cellSize - 1 + offsetY, cellSize * worldDim.width - 1 + offsetX, j * cellSize - 1 + offsetY);
        }*/
        g2d.setColor(Color.black);

        g2d.fillRect(world.getSnake().getHeadPosition().getX() * cellSize + offsetX, world.getSnake().getHeadPosition().getY() * cellSize + offsetY, cellSize - 1, cellSize - 1);

        for (Position pos : world.getSnake().getTailList()) {
            g2d.fillRect(pos.getX() * cellSize + offsetX, pos.getY() * cellSize + offsetY, cellSize - 1, cellSize - 1);
        }

        world.getSnake().getTailList().parallelStream().forEach(schwanzTeil -> {
            Position pos = (Position) schwanzTeil;
            g2d.fillRect(pos.getX() * cellSize + offsetX, pos.getY() * cellSize + offsetY, cellSize - 1, cellSize - 1);
        });

        world.getGoodieSet().parallelStream().forEach(goody -> {
            Position pos = (Position) goody;
            g2d.fillRect(pos.getX() * cellSize + offsetX, pos.getY() * cellSize + offsetY, cellSize - 1, cellSize - 1);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
