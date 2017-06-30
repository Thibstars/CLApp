package be.thibaulthelsmoortel.clapp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private List<String> args;
    // Object list serving as input parameters. Not to be confused with args, being command argument strings.
    private List<Object> input;

    /**
     * Class constructor initialising command and callable.
     *
     * @param command  the command name
     * @param callable the callable to be ran upon execution
     */
    public Command(String command, Callable callable) {
        this.command = command;
        this.callable = callable;
    }

    /**
     * Class constructor initialising command. Use this constructor when you need to access the command in your callable.
     *
     * NOTE: You will have to set the callable afterwards in order to make the command executable.
     * @param command the command name
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Adds arguments to the command.
     *
     * @param args the args to add
     */
    public void addArgs(String... args) {
        if (this.args == null) {
            this.args = new ArrayList<>();
        }
        Collections.addAll(this.args, args);
    }

    /**
     * Adds input objects to the command.
     *
     * @param o the input objects to add
     */
    public void addInput(Object... o) {
        if (input == null) {
            input = new ArrayList<>();
        }
        Collections.addAll(input, o);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(command);

        if (args != null) {
            for (String arg : args) {
                sb.append(" ").append(arg);
            }
        }
        return sb.toString();
    }
}
