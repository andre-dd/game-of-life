package com.gof.render;

import com.gof.game.Board;
import com.gof.game.Cell;

import java.awt.*;

public class BoardRenderer {
    private Board board;

    public BoardRenderer(Board board) {
        this.board = board;
    }

    /**
     * @param g Graphics
     * @param cellSize int
     */
    public void draw(Graphics g, int borderSize, int cellSize) {
        Cell[][] cells = board.getCells();

        int x = borderSize, y = borderSize;
        g.setColor(new Color(48, 48, 48));
        g.fillRect(x, y, cellSize * board.getColumns(), cellSize * board.getRows());

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (!cells[i][j].isAlive()) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x, y, cellSize, cellSize);
                }

                if (cells[i][j].isAlive()) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(x, y, cellSize, cellSize);
                }

                x += cellSize;
            }
            x = borderSize;
            y += cellSize;
        }
    }
}
