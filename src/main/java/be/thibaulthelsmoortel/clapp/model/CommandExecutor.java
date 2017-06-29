package be.thibaulthelsmoortel.clapp.model;

import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

/**
 * Singleton class responsible for executing commands.
 *
 * @author Thibault Helsmoortel
 */
@Log4j2
public class CommandExecutor {

    private static CommandExecutor ourInstance = new CommandExecutor();

    private static Set<Command> commandSet;

    private CommandExecutor() {
        commandSet = new HashSet<>();
    }

    public static boolean add(Command command) {
        return commandSet.add(command);
    }

    public static boolean remove(Command command) {
        return commandSet.remove(command);
    }

    /**
     * Searches for a command from the commandSet and returns it if a command was found.
     *
     * @param command the name of the command to search for
     * @return the matching command, if any
     */
    public static Command find(String command) {
        for (Command c : commandSet) {
            if (c.getCommand().equals(command)) return c;
        }
        throw new IllegalArgumentException(String.format("Command not found: %s", command));
    }

    public static Object execute(Command command) throws Exception {
        if (command == null) throw new IllegalArgumentException("Command must not be null.");
        if (command.getCallable() == null) throw new IllegalArgumentException("Callable must not be null.");

        log.debug("Executing command: {}.", command);
        return command.getCallable().call();
    }

    public static Object execute(String command) throws Exception {
        return execute(find(command));
    }

    public static CommandExecutor getInstance() {
        return ourInstance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
