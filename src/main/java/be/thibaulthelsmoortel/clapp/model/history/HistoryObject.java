package be.thibaulthelsmoortel.clapp.model.history;

import be.thibaulthelsmoortel.clapp.model.Command;
import lombok.Getter;

import java.util.Date;

/**
 * Class representing a command performed at some time.
 *
 * @author Thibault Helsmoortel
 */
@Getter
public final class HistoryObject implements Comparable<HistoryObject> {

    private final Date date;
    private final Command command;

    public HistoryObject(Command command) {
        this.date = new Date();
        this.command = command;
    }

    @Override
    public int compareTo(HistoryObject o) {
        return date.compareTo(o.getDate());
    }
}
