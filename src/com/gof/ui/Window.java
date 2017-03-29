package com.gof.ui;

import com.gof.game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private Board board;
    private ControlPanelMediator controlPanelMediator;

    /**
     * @param board Board
     */
    public Window(Board board, int width, int height, int boarder, int cellSize) {
        super("Conway's Game of Life");

        this.board = board;
        this.controlPanelMediator = new ControlPanelMediator();

        ControlPanelActionListener controlPanelActionListener =
                new ControlPanelActionListener(this, controlPanelMediator, board);

        BoardPanel boardPanel = new BoardPanel(board,boarder, cellSize);

        BoardPanelMouseListener boardPanelActionListener =
                new BoardPanelMouseListener(controlPanelMediator, boardPanel, board);
        boardPanel.addMouseListener(boardPanelActionListener);
        boardPanel.addMouseMotionListener(boardPanelActionListener);

        add(BorderLayout.CENTER, boardPanel);
        add(BorderLayout.SOUTH, new ControlPanel(controlPanelMediator, controlPanelActionListener));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationByPlatform(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.iterate();
        controlPanelMediator.updateIteration(board.getIteration());
        repaint();
    }
}
