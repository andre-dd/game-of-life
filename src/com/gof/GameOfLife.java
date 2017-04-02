package com.gof;

import com.gof.game.Board;
import com.gof.ui.Window;

public class GameOfLife {
    /**
     * @param args String[]
     */
    public static void main(String[] args)  {
        int cellSize = 10;
        int width = 800;
        int height = 600;
        int boarder = 0;
        int rows = 200;
        int columns = 200;

        new Window(new Board(rows, columns), width, height, boarder, cellSize);
    }
}
