package com.gof;

import com.gof.game.Board;
import com.gof.ui.Window;

public class GameOfLife {
    /**
     * @param args String[]
     */
    public static void main(String[] args)  {
        new Window(new Board(50, 76), 800, 600, 2, 10);
    }
}
