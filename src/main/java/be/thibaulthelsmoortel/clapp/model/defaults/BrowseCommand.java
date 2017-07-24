package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Command for browsing to a specified url in the default browser.
 *
 * @author Thibault Helsmoortel
 */
public class BrowseCommand extends Command {

    public BrowseCommand(CLTextArea clTextArea) {
        super("browse");
        setCallable(() -> {
            StringBuilder output = new StringBuilder();
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE) && getArgs() != null) {
                for (String arg : getArgs()) {
                    try {
                        Desktop.getDesktop().browse(new URI(arg));
                        output.append(String.format("Opened '%s' in the default browser.", arg));
                    } catch (IOException | URISyntaxException e) {
                        output.append(e.getLocalizedMessage());
                    }
                    output.append("\n");
                }

            }
            clTextArea.append(output.toString());
            return output.toString();
        });
    }
}
