package be.thibaulthelsmoortel.clapp;

import be.thibaulthelsmoortel.clapp.view.MainFrame;

import javax.swing.*;

/**
 * Application entry point.
 *
 * @author Thibault Helsmoortel
 */
public class CLApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
