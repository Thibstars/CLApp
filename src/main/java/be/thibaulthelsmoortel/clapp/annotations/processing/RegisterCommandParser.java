package be.thibaulthelsmoortel.clapp.annotations.processing;

import be.thibaulthelsmoortel.clapp.annotations.RegisterCommand;
import be.thibaulthelsmoortel.clapp.model.CommandExecutor;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;
import be.thibaulthelsmoortel.clapp.view.ViewConstants;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Log4j2
public class RegisterCommandParser {

    public static void parse() {
        Reflections reflections = new Reflections("be.thibaulthelsmoortel.clapp");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(RegisterCommand.class);

        for (Class<?> command : annotated) {
            try {
                CommandExecutor.add((be.thibaulthelsmoortel.clapp.model.Command) command
                        .getConstructor(CLTextArea.class)
                        .newInstance(ViewConstants.CL_TEXT_AREA));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
