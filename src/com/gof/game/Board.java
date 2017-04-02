package com.gof.game;

public class Board {
    private int rows;
    private int columns;
    private int iteration = 0;
    private Cell[][] cells;
    private Cell[][] initialCells;
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
        this.initialCells = new Cell[rows][columns];

        init();
    }

    /**
     * @return int
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * @return int
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return int
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @return Cell[][]
     */
    public Cell[][] getCells() {
        return cells;
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

    public void resetIteration() {
        iteration = 0;
    }

    public void resetCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].setDead();
            }
        }
    }

    public void saveInitialCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isAlive()) {
                    initialCells[i][j].setAlive();
                    continue;
                }

                initialCells[i][j].setDead();
            }
        }
    }

    public void loadInitialCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (initialCells[i][j].isAlive()) {
                    cells[i][j].setAlive();
                    continue;
                }

                cells[i][j].setDead();
            }
        }
    }

    /**
     * @param row int
     * @param column int
     */
    public void setCellAlive(int row, int column) {
        cells[row][column].setAlive();
    }

    /**
     * @param row int
     * @param column int
     */
    public void setCellDead(int row, int column) {
        cells[row][column].setDead();
    }

    private void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(false);
                initialCells[i][j] = new Cell(false);
            }
        }
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

            if (x < 0) {
                x = rows - 1;
            } else if (x >= rows) {
                x = 0;
            }

            if (y < 0) {
                y = columns - 1;
            } else if (y >= columns) {
                y = 0;
            }

            if (cells[x][y].isAlive()) {
                neighborCount++;
            }
        }

        return neighborCount;
    }
}
