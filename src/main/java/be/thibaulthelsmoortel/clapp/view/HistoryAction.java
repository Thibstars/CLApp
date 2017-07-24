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
    private final Type type;

    public HistoryAction(CLTextArea clTextArea, Type type) {
        this.clTextArea = clTextArea;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case UP:
                showPreviousCommand();
                break;
            case DOWN:
                showNextCommand();
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

    public enum Type {
        UP, DOWN
    }
}
