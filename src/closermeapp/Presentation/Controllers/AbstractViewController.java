package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Util.Notifier;

import javax.swing.*;

/**
 * Created by Andr� on 24/11/2015.
 */
public abstract class AbstractViewController {
    protected Notifier notifier;


    public abstract void openWindow();

    protected void configureWindow(JFrame window) {
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }

    protected abstract void setEvents();

}
