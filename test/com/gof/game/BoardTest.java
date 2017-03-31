package com.gof.game;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
    private Board board;

    public void testBlinkerIteration() {
        assertEquals(0, board.getIteration());

        board.iterate();

        boolean[][] expectedBoard = {
                { false, false, false, false, false, false },
                { false, false, true,  false, false, false },
                { false, false, true,  false, false, false },
                { false, false, true,  false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        assertExpectedBoard(expectedBoard);
        assertEquals(1, board.getIteration());
    }

    public void testGliderIteration() {
        board.resetIteration();
        board.resetCells();

        boolean[][] initBoard = {
                { false, false, false, false, false, false },
                { false, false, true,  false, false, false },
                { false, false, false, true,  false, false },
                { false, true,  true,  true,  false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        initBoard(board, initBoard);

        assertEquals(0, board.getIteration());

        board.iterate();

        boolean[][] expectedBoard = {
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, true,  false, true,  false, false },
                { false, false, true,  true,  false, false },
                { false, false, true,  false, false, false },
                { false, false, false, false, false, false },
        };

        assertExpectedBoard(expectedBoard);
        assertEquals(1, board.getIteration());
    }

    public void testResetIteration() {
        board.iterate();

        assertEquals(1, board.getIteration());

        board.resetIteration();

        assertEquals(0, board.getIteration());

    }

    public void testSetCellAlive() {
        board.setCellAlive(1,2);
        board.setCellAlive(3,2);

        boolean[][] expectedBoard = {
                { false, false, false, false, false, false },
                { false, false, true,  false, false, false },
                { false, true,  true,  true,  false, false },
                { false, false, true,  false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        assertExpectedBoard(expectedBoard);
    }

    public void testSetCellDead() {
        board.setCellDead(2,1);
        board.setCellDead(2,3);

        boolean[][] expectedBoard = {
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, false, true,  false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        assertExpectedBoard(expectedBoard);
    }

    public void testSaveAndLoadInitialCells() {
        board.saveInitialCells();
        board.iterate();
        board.loadInitialCells();

        boolean[][] expectedBoard = {
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, true,  true,  true,  false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        assertExpectedBoard(expectedBoard);
    }

    protected void setUp() {
        board = new Board(6, 6);

        boolean[][] initBoard = {
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, true,  true,  true,  false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
                { false, false, false, false, false, false },
        };

        initBoard(board, initBoard);
    }

    /**
     *
     * @param board Board
     * @param initBoard boolean[][]
     */
    private void initBoard(Board board, boolean[][] initBoard) {
        Cell[][] cells = board.getCells();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (!initBoard[i][j]) {
                    continue;
                }

                cells[i][j].setAlive();
            }
        }
    }

    /**
     * @param expectedBoard boolean[][]
     */
    private void assertExpectedBoard(boolean[][] expectedBoard) {
        Cell[][] cells = board.getCells();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertEquals(expectedBoard[i][j], cells[i][j].isAlive());
            }
        }
    }
}
