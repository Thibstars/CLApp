package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.annotations.RegisterCommand;
import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

/**
 * Command for displaying system properties.
 *
 * @author Thibault Helsmoortel
 */
@RegisterCommand
public class SysPropsCommand extends Command {

    public SysPropsCommand(CLTextArea clTextArea) {
        super("sysProps");
        setCallable(() -> {
            StringBuilder output = new StringBuilder();
            // TODO: 22/08/2017 Add support for a property argument in order to show only 1 property
            System.getProperties().forEach((o, o2) -> clTextArea.append(o.toString() + ": " + o2.toString() + "\n"));
            return output;
        });
    }
}
