package be.thibaulthelsmoortel.clapp.view;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.model.CommandHistory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by thibault.helsmoortel on 30-Jun-17.
 */
public class HistoryAction extends AbstractAction {

    private final CLTextArea clTextArea;
    private final ActionType actionType;

    public HistoryAction(CLTextArea clTextArea, ActionType actionType) {
        this.clTextArea = clTextArea;
        this.actionType = actionType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (actionType) {
            case UP:
                showPreviousCommand();
                break;
            case DOWN:
                showNextCommand();
                break;
            case ESC:
                // No history browsing should be done here
                clTextArea.setText(clTextArea.getText().substring(0, clTextArea.getText().lastIndexOf("\n" + CLTextArea.COMMAND_START) + 2));
                CommandHistory.resetScroller();
                break;
        }
    }

    private void showPreviousCommand() {
        Command previous = CommandHistory.getPrevious();
        if (previous != null) {
            clTextArea.setCommandText(previous.getFullStringCommand());
        }
    }

    private void showNextCommand() {
        Command next = CommandHistory.getNext();
        if (next != null) {
            clTextArea.setCommandText(next.getFullStringCommand());
        }
    }

    public enum ActionType {
        UP, DOWN, ESC
    }
}
