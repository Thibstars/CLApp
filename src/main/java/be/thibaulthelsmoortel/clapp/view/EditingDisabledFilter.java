package be.thibaulthelsmoortel.clapp.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * DocumentFilter preventing editing on text prior to the given position.
 *
 * @author Thibault Helsmoortel
 */
public class EditingDisabledFilter extends DocumentFilter {

    private int promptPosition;

    public EditingDisabledFilter(int promptPosition) {
        this.promptPosition = promptPosition;
    }

    @Override
    public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr) throws BadLocationException {
        if (offset >= promptPosition) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        if (offset >= promptPosition) {
            super.remove(fb, offset, length);
        }
    }

    @Override
    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs) throws BadLocationException {
        if (offset >= promptPosition) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}