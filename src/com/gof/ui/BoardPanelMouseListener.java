package com.gof.ui;

import com.gof.game.Board;

import java.awt.*;
import java.awt.event.*;

public class BoardPanelMouseListener implements MouseMotionListener, MouseListener {
    private ControlPanelMediator controlPanelMediator;
    private BoardPanel boardPanel;
    private Board board;
    private boolean drawingOnBoard;
    private boolean drawAliveCell;

    BoardPanelMouseListener(ControlPanelMediator controlPanelMediator, BoardPanel boardPanel, Board Board) {
        this.controlPanelMediator = controlPanelMediator;
        this.boardPanel = boardPanel;
        this.board = Board;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boardPanel.setMousePosition(new Point(transformColumn(e.getX()), transformColumn(e.getY())));

        if (drawingOnBoard) {
            doDrawOperations(e.getPoint(), drawAliveCell);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boardPanel.setMousePosition(new Point(transformColumn(e.getX()), transformColumn(e.getY())));

        int cursorIcon = controlPanelMediator.isDraw()
                ? Cursor.HAND_CURSOR
                : Cursor.DEFAULT_CURSOR;

        boardPanel.setCursor(new Cursor(cursorIcon));
        boardPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int column = this.transformRow((int)e.getPoint().getX());
        int row = this.transformColumn((int)e.getPoint().getY());

        drawingOnBoard = true;
        drawAliveCell = !board.isCellAlive(row, column);

        doDrawOperations(e.getPoint(), drawAliveCell);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawingOnBoard = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * @param point Point
     */
    private void doDrawOperations(Point point, boolean drawAlive) {
        if (controlPanelMediator.isDraw()) {
            drawCell(point, drawAlive);
        }
    }

    /**
     * @param point Point
     */
    private void drawCell(Point point, boolean drawAlive) {
        int column = this.transformRow((int)point.getX());
        int row = this.transformColumn((int)point.getY());

        if (column < 0 || column >= board.getColumns() || row < 0 || row >= board.getRows()) {
            return;
        }

        if (drawAlive) {
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
