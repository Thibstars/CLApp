package be.thibaulthelsmoortel.clapp.view;

import be.thibaulthelsmoortel.clapp.constants.AppConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame.
 *
 * @author Thibault Helsmoortel
 */
public class MainFrame extends JFrame {

    private CLTextArea clTextArea;

    public MainFrame() throws HeadlessException {
        super(AppConstants.APP_NAME);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initLayout();
        addListeners();
        addComponents();
        Dimension dimension = new Dimension(750, 500);
        setPreferredSize(dimension);
        setSize(dimension);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        add(new JScrollPane(clTextArea), BorderLayout.CENTER);
    }

    private void addListeners() {

    }

    private void initLayout() {
        clTextArea = new CLTextArea();
    }
}
