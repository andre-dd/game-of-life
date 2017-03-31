package com.gof.ui;

import javax.swing.*;
import java.util.HashMap;

class ControlPanel extends JPanel {
    /**
     * @param controlPanelMediator ControlPanelMediator
     */
    ControlPanel(ControlPanelMediator controlPanelMediator, ControlPanelActionListener controlPanelActionListener) {
        JLabel iterationLabel = new JLabel("Iteration: 0");
        controlPanelMediator.registerIterationLabel(iterationLabel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(controlPanelActionListener);
        startButton.setActionCommand(ControlPanelActionListener.Actions.START.name());
        controlPanelMediator.registerStartButton(startButton);

        JToggleButton pauseButton = new JToggleButton("Pause");
        pauseButton.addActionListener(controlPanelActionListener);
        pauseButton.setActionCommand(ControlPanelActionListener.Actions.PAUSE.name());
        pauseButton.setEnabled(false);
        controlPanelMediator.registerPauseButton(pauseButton);

        JToggleButton drawAliveCellButton = new JToggleButton("Draw alive");
        drawAliveCellButton.addActionListener(controlPanelActionListener);
        drawAliveCellButton.setActionCommand(ControlPanelActionListener.Actions.DRAW_ALIVE_CELL.name());
        controlPanelMediator.registerDrawAliveCellButton(drawAliveCellButton);

        JToggleButton drawDeadCellButton = new JToggleButton("Draw dead");
        drawDeadCellButton.addActionListener(controlPanelActionListener);
        drawDeadCellButton.setActionCommand(ControlPanelActionListener.Actions.DRAW_DEAD_CELL.name());
        controlPanelMediator.registerDrawDeadCellButton(drawDeadCellButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(controlPanelActionListener);
        resetButton.setActionCommand(ControlPanelActionListener.Actions.RESET.name());
        resetButton.setEnabled(false);
        controlPanelMediator.registerResetButton(resetButton    );

        JButton clearBoardButton = new JButton("Clear");
        clearBoardButton.addActionListener(controlPanelActionListener);
        clearBoardButton.setActionCommand(ControlPanelActionListener.Actions.CLEAR_BOARD.name());
        controlPanelMediator.registerClearBoardButton(clearBoardButton);

        JComboBox speedComboBox = createSpeedCheckBox();
        controlPanelMediator.registerSpeedComboBox(speedComboBox);

        add(startButton);
        add(pauseButton);
        add(resetButton);
        add(drawAliveCellButton);
        add(drawDeadCellButton);
        add(clearBoardButton);
        add(new JLabel("Speed:"));
        add(speedComboBox);
        add(iterationLabel);
    }

    /**
     * @return JComboBox
     */
    private JComboBox createSpeedCheckBox() {
        HashMap<String, Integer> speed = new HashMap<>();
        speed.put("Very Slow", 800);
        speed.put("Slow", 400);
        speed.put("Normal", 200);
        speed.put("Fast", 100);
        speed.put("Very Fast", 50);

        JComboBox speedComboBox = new JComboBox<>(speed.entrySet().toArray());
        speedComboBox.setSelectedIndex(2);

        return speedComboBox;
    }
}
