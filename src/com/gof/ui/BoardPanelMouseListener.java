package com.gof.ui;

import com.gof.game.Board;

import java.awt.*;
import java.awt.event.*;

public class BoardPanelMouseListener implements MouseMotionListener, MouseListener {
    private ControlPanelMediator controlPanelMediator;
    private BoardPanel boardPanel;
    private Board board;

    BoardPanelMouseListener(ControlPanelMediator controlPanelMediator, BoardPanel boardPanel, Board Board) {
        this.controlPanelMediator = controlPanelMediator;
        this.boardPanel = boardPanel;
        this.board = Board;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boardPanel.setMousePosition(new Point(transformColumn(e.getX()), transformColumn(e.getY())));
        doDrawOperations(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boardPanel.setMousePosition(new Point(transformColumn(e.getX()), transformColumn(e.getY())));
        boardPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        doDrawOperations(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * @param point Point
     */
    private void doDrawOperations(Point point) {
        if (controlPanelMediator.isDrawAliveCell()) {
            drawCell(point, true);
        }

        if (controlPanelMediator.isDrawDeadCell()) {
            drawCell(point, false);
        }
    }

    /**
     * @param point Point
     */
    private void drawCell(Point point, boolean alive) {
        int column = this.transformRow((int)point.getX());
        int row = this.transformColumn((int)point.getY());

        if (column < 0 || column >= board.getColumns() || row < 0 || row >= board.getRows()) {
            return;
        }

        if (alive) {
            board.setCellAlive(row, column);
        } else {
            board.setCellDead(row, column);
        }

        boardPanel.repaint();
    }

    /**
     * @param x int
     *
     * @return int
     */
    private int transformRow(int x) {
        return x / boardPanel.getCellSize() - boardPanel.getBoarder();
    }

    /**
     * @param y int
     *
     * @return int
     */
    private int transformColumn(int y) {
        return y / boardPanel.getCellSize() - boardPanel.getBoarder();
    }
}
