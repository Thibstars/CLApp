package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

/**
 * Command to clear the {@link CLTextArea}.
 *
 * @author Thibault Helsmoortel
 */
public class ClearCommand extends Command {

    public ClearCommand(CLTextArea clTextArea) {
        super("cls", () -> {
            clTextArea.setText(CLTextArea.COMMAND_START);
            return null;
        });
    }
}
