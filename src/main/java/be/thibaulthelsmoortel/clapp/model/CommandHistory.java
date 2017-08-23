package be.thibaulthelsmoortel.clapp.model;

import be.thibaulthelsmoortel.clapp.model.defaults.EmptyCommand;
import be.thibaulthelsmoortel.clapp.model.history.HistoryObject;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class representing executed command history.
 *
 * @author Thibault Helsmoortel
 */
@Getter
@Log4j2
public final class CommandHistory {

    private static final List<HistoryObject> commands = new ArrayList<>();
    private static ListIterator<HistoryObject> scroller;
    private static Command previouslyFetched;
    private static NavigationType previousNavigationType = NavigationType.NONE;

    public static void add(Command command) {
        HistoryObject ho = new HistoryObject(command);
        commands.add(ho);
    }

    public static void resetScroller() {
        scroller = null;
    }

    public static void clear() {
        commands.clear();
    }

    public static Command getPrevious() {
        if (scroller == null) {
            scroller = commands.listIterator(commands.size());
        }
        if (scroller.hasPrevious()) {
            if (previousNavigationType.equals(NavigationType.NEXT)) {
                scroller.previous();
            }
            previouslyFetched = scroller.previous().getCommand();
            previousNavigationType = NavigationType.PREVIOUS;
            return previouslyFetched;
        }
        previousNavigationType = NavigationType.PREVIOUS;
        return previouslyFetched;
    }

    public static Command getNext() {
        if (scroller == null) {
            scroller = commands.listIterator(commands.size());
        }
        if (scroller.hasNext()) {
            if (previousNavigationType.equals(NavigationType.PREVIOUS)) {
                scroller.next();
            }
            try {
                previouslyFetched = scroller.next().getCommand();
            } catch (NoSuchElementException e) {
                previouslyFetched = new EmptyCommand();
            }
            previousNavigationType = NavigationType.NEXT;
            return previouslyFetched;
        }
        previousNavigationType = NavigationType.NEXT;
        return previouslyFetched;
    }

    private enum NavigationType {
        NONE, PREVIOUS, NEXT
    }
}
