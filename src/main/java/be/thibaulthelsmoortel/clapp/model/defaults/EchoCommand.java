package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;
import lombok.extern.log4j.Log4j2;

/**
 * Command to echo the given args in the {@link CLTextArea}.
 *
 * @author Thibault Helsmoortel
 */
@Log4j2
public class EchoCommand extends Command {

    public EchoCommand(CLTextArea clTextArea) {
        super("echo");
        setCallable(() -> {
            String echo = "";
            if (getArgs() != null) {
                StringBuilder args = new StringBuilder();
                for (String s : getArgs()) {
                    args.append(s).append(" ");
                }
                args.deleteCharAt(args.length() - 1); // Delete trailing space
                echo = String.format("%s", args.toString());
                if (echo.startsWith("\"") && echo.endsWith("\"")) {
                    echo = echo.substring(1, echo.length() - 1);
                }
            }
            clTextArea.append(echo);
            return echo;
        });
    }
}
