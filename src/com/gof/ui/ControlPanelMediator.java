package com.gof.ui;

import javax.swing.*;
import java.util.Map;

class ControlPanelMediator {
    private JButton startButton;
    private JButton resetButton;
    private JToggleButton pauseButton;
    private JToggleButton drawAliveCellButton;
    private JToggleButton drawDeadCellButton;
    private JButton clearBoardButton;
    private JComboBox speedComboBox;
    private JLabel iterationLabel;

    /**
     * @param startButton JButton
     */
    void registerStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    /**
     * @param pauseButton JToggleButton
     */
    void registerPauseButton(JToggleButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    /**
     * @param resetButton JButton
     */
    void registerResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }

    /**
     * @param drawAliveCellButton JToggleButton
     */
    void registerDrawAliveCellButton(JToggleButton drawAliveCellButton) {
        this.drawAliveCellButton = drawAliveCellButton;
    }

    /**
     * @param drawDeadCellButton JToggleButton
     */
    void registerDrawDeadCellButton(JToggleButton drawDeadCellButton) {
        this.drawDeadCellButton = drawDeadCellButton;
    }

    /**
     * @param clearBoardButton JButton
     */
    void registerClearBoardButton(JButton clearBoardButton) {
        this.clearBoardButton = clearBoardButton;
    }

    /**
     * @param speedComboBox JComboBox
     */
    void registerSpeedComboBox(JComboBox speedComboBox) {
        this.speedComboBox = speedComboBox;
    }

    /**
     * @param iterationLabel JLabel
     */
    void registerIterationLabel(JLabel iterationLabel) {
        this.iterationLabel = iterationLabel;
    }

    void disableStart() {
        startButton.setEnabled(false);
    }

    void enableStart() {
        startButton.setEnabled(true);
    }

    void enablePause() {
        pauseButton.setEnabled(true);
    }

    void disablePause() {
        pauseButton.setEnabled(false);
    }

    void enableReset() {
        resetButton.setEnabled(true);
    }

    void disableReset() {
        resetButton.setEnabled(false);
    }

    void enableSpeed() {
        speedComboBox.setEnabled(true);
    }

    void disableSpeed() {
        speedComboBox.setEnabled(false);
    }

    void enableCleaBoard() {
        clearBoardButton.setEnabled(true);
    }

    void disableClearBoard() {
        clearBoardButton.setEnabled(false);
    }

    void unsetPause() {
        pauseButton.getModel().setSelected(false);
    }

    void unsetDrawAliveCell() {
        drawAliveCellButton.getModel().setSelected(false);
    }

    void unsetDrawDeadCell() {
        drawDeadCellButton.getModel().setSelected(false);
    }

    /**
     * @return boolean
     */
    boolean isPaused() {
        return pauseButton.getModel().isSelected();
    }

    boolean isDrawAliveCell() {
        return drawAliveCellButton.getModel().isSelected();
    }

    boolean isDrawDeadCell() { return drawDeadCellButton.getModel().isSelected(); }

    /**
     * @param iteration Iteration
     */
    void updateIteration(int iteration) {
        this.iterationLabel.setText("Iteration: " + iteration);
    }

    int getSpeed() {
        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) speedComboBox.getModel().getSelectedItem();

        return entry.getValue();
    }
}

