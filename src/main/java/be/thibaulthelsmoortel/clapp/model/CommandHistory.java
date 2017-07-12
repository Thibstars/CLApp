package be.thibaulthelsmoortel.clapp.model;

import be.thibaulthelsmoortel.clapp.model.history.HistoryObject;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class representing executed command history.
 *
 * @author Thibault Helsmoortel
 */
@Getter
public final class CommandHistory {

    private static final Set<HistoryObject> commandSet = new HashSet<>();

    private static HistoryObject current;

    public static void add(Command command) {
        HistoryObject ho = new HistoryObject(command);
        if (CollectionUtils.isEmpty(commandSet)) {
            current = ho;
        }
        commandSet.add(ho);
    }

    public static void clear() {
        commandSet.clear();
    }

    public static Command getPrevious() {
        // TODO: 30-Jun-17 properly get previous command
        List<HistoryObject> commands = new ArrayList<>(commandSet);
        commands.sort(HistoryObject::compareTo);
        commands = commands.stream().filter(historyObject -> historyObject.getDate().after(current.getDate())).collect(Collectors.toList());
        current = commands.get(0);
        return commands.get(0).getCommand();
    }

    public static Command getNext() {
        // TODO: 30-Jun-17 properly get next command
        List<HistoryObject> commands = new ArrayList<>(commandSet);
        commands.sort(HistoryObject::compareTo);
        commands = commands.stream().filter(historyObject -> historyObject.getDate().before(current.getDate())).collect(Collectors.toList());
        current = commands.get(0);
        return commands.get(0).getCommand();
    }
}
