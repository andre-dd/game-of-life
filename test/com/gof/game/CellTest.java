package com.gof.game;

import junit.framework.TestCase;

public class CellTest extends TestCase {
    public void testCellSetDead() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive());

        cell.setDead();
        assertFalse(cell.isAlive());
    }

    public void testCellSetAlive() {
        Cell cell = new Cell(false);
        assertFalse(cell.isAlive());

        cell.setAlive();
        assertTrue(cell.isAlive());
    }

    public void testCellUpdateStateToDead() {
        Cell cell = new Cell(true);

        cell.setUpdateDead();
        cell.update();
        assertFalse(cell.isAlive());
    }

    public void testCellUpdateStateToAlive() {
        Cell cell = new Cell(false);

        cell.setUpdateAlive();
        cell.update();
        assertTrue(cell.isAlive());
    }
}
