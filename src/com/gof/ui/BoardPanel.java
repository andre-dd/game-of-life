package com.gof.ui;

import com.gof.game.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int boarder;
    private int cellSize;
    private int boarderSize;
    private Board board;

    /**
     * Constructor
     */
    BoardPanel(Board board, int boarder, int cellSize) {
        this.board = board;
        this.cellSize = cellSize;
        this.boarder = boarder;
        this.boarderSize = boarder * cellSize;
    }

    /**
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        board.draw(g, boarderSize, cellSize);
    }

    /**
     * @return int
     */
    int getBoarder() {
        return boarder;
    }

    /**
     * @return int
     */
    int getCellSize() {
        return cellSize;
    }
}
