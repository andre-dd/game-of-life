package com.gof.ui;

import com.gof.render.BoardRenderer;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int borderCells;
    private int cellSize;
    private int borderSize;
    private BoardRenderer boardRenderer;
    private ControlPanelMediator controlPanelMediator;
    private Point mousePosition;

    /**
     * Constructor
     */
    BoardPanel(BoardRenderer boardRenderer, ControlPanelMediator controlPanelMediator, int borderCells, int cellSize) {
        this.boardRenderer = boardRenderer;
        this.controlPanelMediator = controlPanelMediator;
        this.cellSize = cellSize;
        this.borderCells = borderCells;
        this.borderSize = borderCells * cellSize;
        this.mousePosition = new Point();
    }

    /**
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        boardRenderer.draw(g, borderSize, cellSize);

        if (controlPanelMediator.isDraw()) {
            g.setColor(Color.ORANGE);
            g.fillRect(mousePosition.x * cellSize + borderSize, mousePosition.y * cellSize + borderSize, cellSize, cellSize);
        }
    }

    /**
     * @param mousePosition Point
     */
    void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    /**
     * @return int
     */
    int getBorderCells() {
        return borderCells;
    }

    /**
     * @return int
     */
    int getCellSize() {
        return cellSize;
    }
}
