package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

import java.io.IOException;
import java.util.Scanner;

/**
 * Command to perform cmd commands.
 *
 * @author Thibault Helsmoortel
 */
public class CmdCommand extends Command {

    private final CLTextArea clTextArea;

    public CmdCommand(CLTextArea clTextArea) {
        super("cmd");
        this.clTextArea = clTextArea;
        setCallable(() -> {
            StringBuilder fullCommand = new StringBuilder();
            if (getArgs() != null) {
                for (String s : getArgs()) {
                    fullCommand.append(" ").append(s);
                }
            }
            return execCmd(fullCommand.toString());
        });
    }

    private String execCmd(String cmd) {
        Scanner s;
        try {
            s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        } catch (IOException e) {
            clTextArea.append(e.getLocalizedMessage());
            return e.getLocalizedMessage();
        }
        StringBuilder output = new StringBuilder();
        while (s.hasNext()) {
            String next = s.next();
            clTextArea.append(next);
            output.append(next);
        }
        return output.toString();
    }
}
