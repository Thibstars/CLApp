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
public final class CommandExecutor {

    private static Set<Command> commandSet = new HashSet<>();

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

    private static Object execute(Command command) throws Exception {
        if (command == null) throw new IllegalArgumentException("Command must not be null.");
        if (command.getCallable() == null) throw new IllegalArgumentException("Callable must not be null.");

        log.debug("Executing command: {}.", command);
        Object result = command.getCallable().call();
        // Reset args and inputs
        command.setArgs(null);
        command.setInput(null);
        return result;
    }

    public static Object execute(String commandString) throws Exception {
        String[] words = commandString.split(" ");
        Command command = find(words[0]);
        for (int i = 1; i < words.length; i++) {
            command.addArgs(words[i]);
        }
        return execute(command);
    }
}
