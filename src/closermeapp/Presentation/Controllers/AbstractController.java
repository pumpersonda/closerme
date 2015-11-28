package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Util.Notifier;

/**
 * Created by Andr� on 24/11/2015.
 */
public abstract class AbstractController {
    protected Notifier notification;

    protected abstract void setEvents();

    protected abstract void openWindow();

}
