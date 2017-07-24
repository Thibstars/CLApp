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
            String output = "";
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE) && getArgs() != null) {
                try {
                    Desktop.getDesktop().browse(new URI(getArgs().get(0)));
                    output = String.format("Opened '%s' in the default browser.", getArgs().get(0));
                } catch (IOException | URISyntaxException e) {
                    output = e.getLocalizedMessage();
                }
            }
            clTextArea.append(output);
            return output;
        });
    }
}
