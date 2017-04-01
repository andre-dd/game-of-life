package com.gof;

import com.gof.game.Board;
import com.gof.ui.Window;

public class GameOfLife {
    /**
     * @param args String[]
     */
    public static void main(String[] args)  {
        int cellSize = 10;
        int width = 1020;
        int height = 720;
        int boarder = 0;
        int rows = 66;
        int columns = 102;

        new Window(new Board(rows, columns), width, height, boarder, cellSize);
    }
}
