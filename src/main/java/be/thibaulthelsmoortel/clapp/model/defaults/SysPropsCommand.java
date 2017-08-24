package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.annotations.RegisterCommand;
import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;
import org.apache.commons.collections.CollectionUtils;

/**
 * Command for displaying system properties.
 *
 * @author Thibault Helsmoortel
 */
@RegisterCommand
public class SysPropsCommand extends Command {

    public SysPropsCommand(CLTextArea clTextArea) {
        super("sysProps");
        setCallable(() -> {
            StringBuilder output = new StringBuilder();
            if (CollectionUtils.isNotEmpty(getArgs())) {
                for (String arg : getArgs()) {
                    String result = arg + ": " + System.getProperty(arg) + "\n";
                    output.append(result);
                    clTextArea.append(result);
                }
            } else {
                System.getProperties().forEach((o, o2) -> {
                    String result = o.toString() + ": " + o2.toString() + "\n";
                    output.append(result);
                    clTextArea.append(result);
                });
            }

            return output;
        });
    }
}
