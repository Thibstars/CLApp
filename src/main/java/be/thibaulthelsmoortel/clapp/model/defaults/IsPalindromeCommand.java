package be.thibaulthelsmoortel.clapp.model.defaults;

import be.thibaulthelsmoortel.clapp.annotations.RegisterCommand;
import be.thibaulthelsmoortel.clapp.model.Command;
import be.thibaulthelsmoortel.clapp.view.CLTextArea;

/**
 * Command checking whether a word is a palindrome or not.
 *
 * @author Thibault Helsmoortel
 */
@RegisterCommand
public class IsPalindromeCommand extends Command {

    public IsPalindromeCommand(CLTextArea clTextArea) {
        super("isPalindrome");
        setCallable(() -> {
            StringBuilder output = new StringBuilder();
            if (getArgs() != null) {
                for (String word : getArgs()) {
                    boolean isPal = isPalindrome(word);
                    output.append(String.format("'%s' is %s a palindrome.\n", word, isPal ? "" : "not "));
                }
            }
            output.deleteCharAt(output.length() - 1); // Delete trailing newline
            clTextArea.append(output.toString());
            return output.toString();
        });
    }

    private static boolean isPalindrome(String word) {
        return word.equalsIgnoreCase(new StringBuilder(word).reverse().toString());
    }
}
