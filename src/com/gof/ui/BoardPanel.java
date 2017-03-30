package com.gof.ui;

import com.gof.render.BoardRenderer;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int boarder;
    private int cellSize;
    private int boarderSize;
    private BoardRenderer boardRenderer;

    /**
     * Constructor
     */
    BoardPanel(BoardRenderer boardRenderer, int boarder, int cellSize) {
        this.boardRenderer = boardRenderer;
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

        boardRenderer.draw(g, boarderSize, cellSize);
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
