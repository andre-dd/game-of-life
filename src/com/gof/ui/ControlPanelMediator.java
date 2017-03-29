package com.gof.ui;

import javax.swing.*;
import java.util.Map;

class ControlPanelMediator {
    private JButton startButton;
    private JButton resetButton;
    private JToggleButton pauseButton;
    private JToggleButton editButton;
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
     * @param editButton JToggleButton
     */
    void registerEditButton(JToggleButton editButton) {
        this.editButton = editButton;
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

    boolean isPaused() {
        return pauseButton.getModel().isSelected();
    }

    void unsetPause() {
        pauseButton.getModel().setSelected(false);
    }

    void enablePause() {
        pauseButton.setEnabled(true);
    }

    void disablePause() {
        pauseButton.setEnabled(false);
    }

    boolean isEdit() {
        return editButton.getModel().isSelected();
    }

    /**
     * @param iteration Iteration
     */
    void updateIteration(int iteration) {
        this.iterationLabel.setText("Iteration: " + iteration);
    }

    void enableReset() {
        resetButton.setEnabled(true);
    }

    void disableReset() {
        resetButton.setEnabled(false);
    }

    int getSpeed() {
        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) speedComboBox.getModel().getSelectedItem();

        return entry.getValue();
    }

    void enableSpeed() {
        speedComboBox.setEnabled(true);
    }

    void disableSpeed() {
        speedComboBox.setEnabled(false);
    }
}
