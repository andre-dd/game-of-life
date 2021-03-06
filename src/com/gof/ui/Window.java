package com.gof.ui;

import com.gof.game.Board;
import com.gof.render.BoardRenderer;

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
    public Window(Board board, int width, int height, int border, int cellSize) {
        super("Conway's Game of Life");

        this.board = board;
        this.controlPanelMediator = new ControlPanelMediator();

        ControlPanelActionListener controlPanelActionListener =
                new ControlPanelActionListener(this, controlPanelMediator, board);

        BoardPanel boardPanel = new BoardPanel(new BoardRenderer(board), controlPanelMediator, border, cellSize);

        boardPanel.setPreferredSize(
                new Dimension(board.getColumns() * cellSize, board.getRows() * cellSize)
        );
        BoardPanelMouseListener boardPanelActionListener =
                new BoardPanelMouseListener(controlPanelMediator, boardPanel, board);
        boardPanel.addMouseListener(boardPanelActionListener);
        boardPanel.addMouseMotionListener(boardPanelActionListener);

        add(BorderLayout.CENTER, new JScrollPane(boardPanel));
        add(BorderLayout.SOUTH, new ControlPanel(controlPanelMediator, controlPanelActionListener));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationByPlatform(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.iterate();
        controlPanelMediator.updateIteration(board.getIteration());
        repaint();
    }
}
