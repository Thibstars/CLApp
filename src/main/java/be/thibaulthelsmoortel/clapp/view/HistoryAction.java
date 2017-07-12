package be.thibaulthelsmoortel.clapp.view;

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
        clTextArea.setCommandText(CommandHistory.getPrevious().getFullStringCommand());
    }

    private void showNextCommand() {
        clTextArea.setCommandText(CommandHistory.getNext().getFullStringCommand());
    }

    public enum Type {
        UP, DOWN
    }
}
