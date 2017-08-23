package be.thibaulthelsmoortel.clapp.view;

import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.model.CommandExecutor;
import be.thibaulthelsmoortel.clapp.model.defaults.ClearCommand;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
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

    static final String COMMAND_START = ">";

    public CLTextArea() {
        super();
        initLayout();
        disableKeys();
        customizeKeys();
        addListeners();
    }

    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String lastLine = getLastLine();
                if (e.getKeyChar() == '\n') {
                    try {
                        String command = getText().substring(getText().lastIndexOf(COMMAND_START) + 1, getText().length() - 1);
                        if (StringUtils.isNotEmpty(command) && !command.equals("\n")) {
                            Command c = CommandExecutor.find(command.split(" ")[0]);
                            enableEditing();
                            CommandExecutor.execute(command);
                            if (c instanceof ClearCommand) {
                                setText(COMMAND_START);
                            } else {
                                append("\n" + COMMAND_START);
                            }
                            disablePreviousText();
                        } else {
                            enableEditing();
                            setText(getText(0, getText().lastIndexOf('\n')));
                            disablePreviousText();
                        }
                    } catch (IllegalArgumentException e1) {
                        setText(getText() + e1.getMessage() + "\n" + COMMAND_START);
                        disablePreviousText();
                    } catch (Exception e1) {
                        log.error(e1.getMessage(), e1);
                    }
                } else if (e.getKeyChar() == '\b' && getCaretPosition() == 0) {
                    // Prevent removal on first line
                    setText(COMMAND_START);
                } else if (e.getKeyChar() == '\b' && getCaretPosition() == getText().lastIndexOf(lastLine) + 1 && !getText().equals(COMMAND_START)) {
                    // Prevent removal on next lines
                    setText(getText() + COMMAND_START);
                }
            }
        });
    }

    private void disableKeys() {
        // Remove default key behavior
        String[] keys = {"UP", "DOWN"};
        for (String key : keys) {
            getInputMap().put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    private void customizeKeys() {
        // Apply custom key behavior
        InputMap inputMap = getInputMap();
        KeyStroke up = KeyStroke.getKeyStroke("UP");
        KeyStroke down = KeyStroke.getKeyStroke("DOWN");
        KeyStroke esc = KeyStroke.getKeyStroke("ESCAPE");
        getActionMap().put(up, new HistoryAction(this, HistoryAction.ActionType.UP));
        getActionMap().put(down, new HistoryAction(this, HistoryAction.ActionType.DOWN));
        getActionMap().put(esc, new HistoryAction(this, HistoryAction.ActionType.ESC));
        inputMap.put(up, up);
        inputMap.put(down, down);
        inputMap.put(esc, esc);
    }

    private String getLastLine() {
        return getText().contains("\n") ? getText().substring(getText().lastIndexOf('\n')) : getText();
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

    private void disablePreviousText() {
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new EditingDisabledFilter(getCaretPosition()));
    }

    private void enableEditing() {
        ((AbstractDocument) this.getDocument()).setDocumentFilter(null);
    }

    public void setCommandText(String commandText) {
        enableEditing();
        setText(getText().substring(0, getText().lastIndexOf("\n" + COMMAND_START) + 1));
        append(COMMAND_START + commandText);
    }
}
