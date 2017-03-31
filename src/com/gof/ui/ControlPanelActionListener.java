package com.gof.ui;

import com.gof.game.Board;

import javax.swing.*;
import java.awt.event.*;

public class ControlPanelActionListener implements ActionListener {
    enum Actions {
        START, PAUSE, RESET, DRAW_ALIVE_CELL, DRAW_DEAD_CELL, CLEAR_BOARD
    }

    private Timer timer;
    private Window window;
    private ControlPanelMediator controlPanelMediator;
    private Board board;

    /**
     * @param board board
     */
    ControlPanelActionListener(Window window, ControlPanelMediator controlPanelMediator, Board board) {
        this.window = window;
        this.controlPanelMediator = controlPanelMediator;
        this.board = board;
    }

    /**
     * @param event ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(Actions.START.name())) {
            board.saveInitialCells();

            timer = new Timer(controlPanelMediator.getSpeed(), window);
            timer.start();

            controlPanelMediator.disableStart();
            controlPanelMediator.disableSpeed();
            controlPanelMediator.disableClearBoard();
            controlPanelMediator.enablePause();
            controlPanelMediator.enableReset();
        }

        if (event.getActionCommand().equals(Actions.PAUSE.name())) {
            if (controlPanelMediator.isPaused()) {
                timer.stop();
                return;
            }

            timer.start();
        }

        if (event.getActionCommand().equals(Actions.RESET.name())) {
            timer.stop();

            controlPanelMediator.enableStart();
            controlPanelMediator.enableSpeed();
            controlPanelMediator.enableCleaBoard();
            controlPanelMediator.unsetPause();
            controlPanelMediator.disablePause();
            controlPanelMediator.disableReset();

            board.resetIteration();
            board.loadInitialCells();

            controlPanelMediator.updateIteration(board.getIteration());
            window.repaint();
        }

        if (event.getActionCommand().equals(Actions.DRAW_ALIVE_CELL.name())) {
            controlPanelMediator.unsetDrawDeadCell();
        }

        if (event.getActionCommand().equals(Actions.DRAW_DEAD_CELL.name())) {
            controlPanelMediator.unsetDrawAliveCell();
        }

        if (event.getActionCommand().equals(Actions.CLEAR_BOARD.name())) {
            board.resetCells();
            window.repaint();
        }
    }
}
