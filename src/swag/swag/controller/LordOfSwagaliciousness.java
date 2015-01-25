package swag.swag.controller;

import swag.swag.model.Game;
import swag.swag.model.World;
import swag.swag.view.Display;

/**
 * Created by flofincke on 24/01/15.
 */
public class LordOfSwagaliciousness {

    Game game;
    Display nokia;
    TikTok tikTok;


    public LordOfSwagaliciousness(Game game) {
        this.game = game;
        this.nokia = new Display(game.getWorld(), this);
        this.game.getWorld().addObserver(this.nokia.getPlayground());
        this.nokia.getPlayground().addKeyListener(new MovingTingALing(this));
        this.nokia.getPlayground().setFocusable(true);
    }

    public void startGame() {
        this.nokia.getPlayground().requestFocus();
        tikTok = new TikTok(this);
        this.game.startGame();
        tikTok.start();
        nokia.getStartButton().setEnabled(false);
        nokia.getStopButton().setEnabled(true);
        this.nokia.getStartButton().setEnabled(false);

    }

    public void endGame() {
        tikTok.setPaused(true);
        this.game.endGame();
        nokia.getStopButton().setEnabled(false);
        nokia.getResetButton().setEnabled(true);
    }

    public void resetGame() {
        this.game.resetGame();
        this.nokia.getStartButton().setEnabled(true);
    }

    public void makeStep() {
        this.game.advanceToNextRound();
    }

    public Game getGame() {
        return this.game;
    }

    private class TikTok extends Thread{

        private LordOfSwagaliciousness lord;
        private boolean paused;

        public TikTok(LordOfSwagaliciousness lord) {
            super();
            this.lord = lord;
            this.paused = false;
        }

        public void run() {
            while(!paused) {
                    lord.makeStep();
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                    }
            }
        }

        public boolean isPaused() {
            return paused;
        }

        public void setPaused(boolean paused) {
            this.paused = paused;
        }

    }
}
