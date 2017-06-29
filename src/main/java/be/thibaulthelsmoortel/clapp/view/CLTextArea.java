package be.thibaulthelsmoortel.clapp.view;

import be.thibaulthelsmoortel.clapp.model.CommandExecutor;
import be.thibaulthelsmoortel.clapp.model.defaults.ClearCommand;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Main component for text (command) input.
 *
 * @author Thibault Helsmoortel
 */
@Log4j2
public class CLTextArea extends JTextArea {

    public static final String COMMAND_START = ">";

    public CLTextArea() {
        super();
        initLayout();
        addCommands();
        addListeners();
    }

    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    try {
                        String command = getText().substring(getText().lastIndexOf(COMMAND_START) + 1, getText().length() - 1);
                        if (StringUtils.isNotEmpty(command) && !command.equals("\n")) {
                            CommandExecutor.execute(command);
                        } else {
                            setText(getText(0, getText().lastIndexOf('\n')));
                        }
                    } catch (IllegalArgumentException e1) {
                        setText(getText() + e1.getMessage() + "\n" + COMMAND_START);
                    } catch (Exception e1) {
                        log.error(e1.getMessage(), e1);
                    }
                }
            }
        });
    }

    private void initLayout() {
        setWrapStyleWord(true);
        setLineWrap(true);
        DefaultCaret caret = new DefaultCaret();
        caret.setBlinkRate(750);
        setCaret(caret);
        setCaretColor(Color.WHITE);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(new Font("sans-serif", Font.PLAIN, 14));
        setText(COMMAND_START);
    }

    private void addCommands() {
        CommandExecutor.add(new ClearCommand(this));
    }
}
