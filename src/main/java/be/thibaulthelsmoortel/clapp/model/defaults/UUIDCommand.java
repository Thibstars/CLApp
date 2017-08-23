package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.annotations.RegisterCommand;
import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

import java.util.UUID;

/**
 * Command for displaying a random UUID.
 *
 * @author Thibault Helsmoortel
 */
@RegisterCommand
public class UUIDCommand extends Command {

    public UUIDCommand(CLTextArea clTextArea) {
        super("uuid");
        setCallable(() -> {
            String output = UUID.randomUUID().toString();
            clTextArea.append(output);
            return output;
        });
    }
}
