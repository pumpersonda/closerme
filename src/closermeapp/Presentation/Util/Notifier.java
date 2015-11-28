package closermeapp.Presentation.Util;

import javax.swing.*;

/**
 * Created by Andr� on 22/11/2015.
 */
public class Notifier {

    public Notifier() {
    }

    public void showSuccessMessage(String title, String message) {
        int PLAIN_MESSAGE = JOptionPane.PLAIN_MESSAGE;
        displayMessage(title, message, PLAIN_MESSAGE);
    }

    public void showFailMessage(String title, String message) {
        int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
        displayMessage(title, message, ERROR_MESSAGE);
    }

    public void showWarningMessage(String title, String message) {
        int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
        displayMessage(title, message, WARNING_MESSAGE);
    }

    public void displayMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }


}
