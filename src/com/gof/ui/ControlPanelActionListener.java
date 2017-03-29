package com.gof.ui;

import com.gof.game.Board;

import javax.swing.*;
import java.awt.event.*;

public class ControlPanelActionListener implements ActionListener {
    enum Actions {
        START, PAUSE, RESET, EDIT
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
            timer = new Timer(controlPanelMediator.getSpeed(), window);
            timer.start();

            controlPanelMediator.disableStart();
            controlPanelMediator.disableSpeed();
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
            controlPanelMediator.unsetPause();
            controlPanelMediator.disablePause();
            controlPanelMediator.disableReset();

            board.reset();

            controlPanelMediator.updateIteration(board.getIteration());
            window.repaint();
        }
    }
}