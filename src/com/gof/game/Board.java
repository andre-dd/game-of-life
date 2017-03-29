package com.gof.game;

import java.awt.*;
import java.util.Random;

public class Board {
    private int rows;
    private int columns;
    private int iteration = 0;
    private Cell[][] cells;
    private int[][] neighborCells = {
        {-1, -1}, {0, -1}, {1, -1},
        {-1,  0},          {1,  0},
        {-1,  1}, {0,  1}, {1,  1},
    };

    /**
     * @param rows int
     * @param columns int
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];

        init();
        initStartingCells(cells);
    }

    public int getIteration() {
        return iteration;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     * @param g Graphics
     * @param cellSize int
     */
    public void draw(Graphics g, int boarderSize, int cellSize) {
        int x = boarderSize, y = boarderSize;

        g.setColor(Color.WHITE);
        g.fillRect(x, y, cellSize * columns, cellSize * rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                g.setColor(Color.BLACK);
                if (!cells[i][j].isAlive()) {
                    g.drawRect(x, y, cellSize, cellSize);
                }

                if (cells[i][j].isAlive()) {
                    g.fillRect(x, y, cellSize, cellSize);
                }

                x += cellSize;
            }
            x = boarderSize;
            y += cellSize;
        }
    }

    /**
     * Rules:
     *  - A dead cell with three live neighbors becomes alive
     *  - A live cell with less than two live neighbors becomes dead
     *  - A live cell with more than three live neighbors becomes dead
     *  - A live cell with two or three live neighbors stays alive
     */
    public void iterate() {
        iteration++;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean isAlive = cells[i][j].isAlive();
                int numberOfNeighbors = countNeighbors(i, j);

                if (!isAlive && 3 == numberOfNeighbors) {
                    cells[i][j].setUpdateAlive();
                }

                if (isAlive && 2 > numberOfNeighbors) {
                    cells[i][j].setUpdateDead();
                }

                if (isAlive && 3 < numberOfNeighbors) {
                    cells[i][j].setUpdateDead();
                }

                if (isAlive && (2 == numberOfNeighbors || 3 == numberOfNeighbors)) {
                    cells[i][j].setUpdateAlive();
                }
            }
        }

        updateCells();
    }

    public void reset() {
        init();
        iteration = 0;
    }

    /**
     * @param row int
     * @param column int
     */
    public void setCellAlive(int row, int column) {
        cells[row][column].setAlive();
    }

    private void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    /**
     * @param cells Cell[][]
     */
    private void initStartingCells(Cell[][] cells) {
        int offsetX = 22;
        int offsetY = 12;

        cells[offsetY+1][offsetX+2].setAlive();
        cells[offsetY+2][offsetX+3].setAlive();
        cells[offsetY+3][offsetX+1].setAlive();
        cells[offsetY+3][offsetX+2].setAlive();
        cells[offsetY+3][offsetX+3].setAlive();

        int offsetXCellsOne = offsetX + 10; int offsetYCellsOne = offsetY + 10;
        cells[offsetYCellsOne+1][offsetXCellsOne+4].setAlive();
        cells[offsetYCellsOne+2][offsetXCellsOne+3].setAlive();
        cells[offsetYCellsOne+2][offsetXCellsOne+1].setAlive();
        cells[offsetYCellsOne+3][offsetXCellsOne+2].setAlive();
        cells[offsetYCellsOne+3][offsetXCellsOne+3].setAlive();

        int offsetXCellTwo = offsetX + 20; int offsetYCellsTwo = offsetY + 20;
        cells[offsetYCellsTwo+1][offsetXCellTwo+3].setAlive();
        cells[offsetYCellsTwo+2][offsetXCellTwo+3].setAlive();
        cells[offsetYCellsTwo+2][offsetXCellTwo+1].setAlive();
        cells[offsetYCellsTwo+2][offsetXCellTwo+2].setAlive();
        cells[offsetYCellsTwo+3][offsetXCellTwo+3].setAlive();
    }

    private void updateCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].update();
            }
        }
    }

    /**
     * @param i int
     * @param j int
     *
     * @return int
     */
    private int countNeighbors(int i, int j) {
        int neighborCount = 0;

        for (int[] neighborCell: neighborCells) {
            int x = i + neighborCell[0];
            int y = j + neighborCell[1];

            if (!(x > 0 && x < rows && y > 0 && y < columns)) {
                continue;
            }

            if (cells[x][y].isAlive()) {
                neighborCount++;
            }
        }

        return neighborCount;
    }
}
