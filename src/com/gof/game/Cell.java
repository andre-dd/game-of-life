package com.gof.game;

class Cell {
    private boolean alive;
    private boolean updateAlive;

    /**
     * @param alive boolean
     */
    Cell(boolean alive) {
        this.alive = alive;
        this.updateAlive = alive;
    }

    void update() {
        alive = updateAlive;
    }

    /**
     * @return boolean
     */
    boolean isAlive() {
        return alive;
    }

    void setAlive() {
        this.alive = true;
    }

    void setDead() {
        this.alive = false;
    }

    void setUpdateAlive() {
        this.updateAlive = true;
    }

    void setUpdateDead() {
        this.updateAlive = false;
    }
}