package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.model.Command;

/**
 * Command to be used by {@link be.thibaulthelsmoortel.clapp.model.CommandHistory}.
 * Note: this command is not to be registered.
 *
 * @author Thibault Helsmoortel
 */
public class EmptyCommand extends Command {

    public EmptyCommand() {
        super("");
    }
}
