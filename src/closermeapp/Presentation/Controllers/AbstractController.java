package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Util.Notifier;

import javax.swing.*;

/**
 * Created by Andr� on 24/11/2015.
 */
public abstract class AbstractController {
    protected Notifier notifier;

    protected abstract void openWindow();

    protected abstract void setEvents();

    protected void configureWindow(JFrame window) {
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        //initializeView();
    }


}
