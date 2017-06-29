package be.thibaulthelsmoortel.clapp.model;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * Class representing a command.
 *
 * @author Thibault Helsmoortel
 */
@Data
public class Command {

    protected final String command;
    private Callable callable;
    private final String[] args;

    public Command(String command, Callable callable, String... args) {
        this.command = command;
        this.callable = callable;
        this.args = args;
    }

    public Command(String command, String... args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(command);

        for (String arg : args) {
            sb.append(" ").append(arg);
        }
        return sb.toString();
    }
}
