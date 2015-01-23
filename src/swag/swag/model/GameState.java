package swag.swag.model;

/**
 * Created by BJOERN on 23.01.2015.
 *
 * valid state transitions are: NOT_STARTED -> RUNNING, RUNNING->ENDED, ENDED -> NOT_STARTED
 */
public enum GameState {
    NOT_STARTED, RUNNING, ENDED
}
