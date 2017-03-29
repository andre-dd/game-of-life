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

        JToggleButton editButton = new JToggleButton("Draw Cells");
        editButton.addActionListener(controlPanelActionListener);
        editButton.setActionCommand(ControlPanelActionListener.Actions.EDIT.name());
        controlPanelMediator.registerEditButton(editButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(controlPanelActionListener);
        resetButton.setActionCommand(ControlPanelActionListener.Actions.RESET.name());
        resetButton.setEnabled(false);
        controlPanelMediator.registerResetButton(resetButton    );

        JComboBox speedComboBox = createSpeedCheckBox();
        controlPanelMediator.registerSpeedComboBox(speedComboBox);

        add(startButton);
        add(pauseButton);
        add(resetButton);
        add(editButton);
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
