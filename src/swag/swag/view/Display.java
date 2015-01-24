package swag.swag.view;

import swag.swag.controller.LordOfSwagaliciousness;
import swag.swag.model.Geometry.Direction;
import swag.swag.model.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by flofincke on 23/01/15.
 */
public class Display extends JFrame{

    private static final long serialVersionUID = 1L;
    private static final int CONFIGWIDTH = 100, CONFIGHEIGHT = 180;
    private JFrame frame;
    private BlackWhitePlayground playground;
    private JFrame playgroundFrame;
    private int cellSize;
    private JButton startButton, stopButton, resetButton;
    private Image stein;

    private World world;
    private LordOfSwagaliciousness lord;

    public Display(World world,LordOfSwagaliciousness lord) {

        this.world = world;
        this.lord = lord;

        frame = new JFrame();
        frame.setSize(CONFIGWIDTH, CONFIGHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // The menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        //MenuItems and ActionListener
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitButtonLis());

        menu.add(exit);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // The main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        // The button panel in South Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(70, 30));
        startButton.addActionListener(new RunListener());
        startButton.setEnabled(true);

        stopButton = new JButton("End");
        stopButton.setPreferredSize(new Dimension(70, 30));
        stopButton.addActionListener(new EndListener());
        stopButton.setEnabled(false);

        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(70, 30));
        resetButton.addActionListener(new ResetListener());
        resetButton.setEnabled(false);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        cellSize = 6;
        showPlayground();

    }

    /**
     * builds and shows Playground
     */
    public void showPlayground() {
        playgroundFrame = new JFrame();
        playgroundFrame.setSize(468, 1024);
        playgroundFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playgroundFrame.setTitle("Life is a Rollercoaster");
        URL path = this.getClass().getResource("stein.jpg");
        try {
            stein = ImageIO.read(new File(path.getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playgroundFrame.add(new JLabel(new ImageIcon(stein)), BorderLayout.CENTER);
        playground = new BlackWhitePlayground(cellSize, world);
        playgroundFrame.setGlassPane(playground);
        playground.repaint();
        playground.setVisible(true);
        playgroundFrame.setResizable(false);
        playgroundFrame.setLocationRelativeTo(null);
        //Show the window.
        playgroundFrame.pack();
        playgroundFrame.setVisible(true);
    }

    public BlackWhitePlayground getPlayground() {
        return this.playground;
    }

    public void showError(String title, String message) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    //Menu-Listener
    class ExitButtonLis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }

    }

    class RunListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            lord.startGame();
            /*int steps;

            if (Integer.parseInt(enterSteps.getText()) == Integer.parseInt(currentStep.getText())) {
                currentStep.setText("0");
                //controller.setStep(0);
            }

            if (currentStep.getText() == "0") {
                try {
                    steps = Integer.parseInt(enterSteps.getText());
                } catch (Exception e2) {
                    showError("Input Error", "War wohl keine Zahl");
                    return;
                }
                //controller.step(steps);
            } else {
                //controller.step(Integer.parseInt(enterSteps.getText()) - Integer.parseInt(currentStep.getText()));
            }*/

        }

    }

    class EndListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            lord.endGame();
        }
    }

    class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            lord.resetGame();
        }
    }
}
