package closermeapp.Presentation.Util;

import javax.swing.*;

/**
 * Created by André on 22/11/2015.
 */
public class NotificationMessage {

    public NotificationMessage() {
    }

    public void showSuccessMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public void showFailMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

    }

    public void showWarningMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
