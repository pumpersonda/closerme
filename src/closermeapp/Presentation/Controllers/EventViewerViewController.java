package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.EventManagement.EventViewer;

import javax.swing.*;

/**
 * Created by JoseJulio on 30/11/2015.
 */
public class EventViewerViewController extends AbstractViewController {

    EventViewer eventViewer;
    EventRegistrationViewController eventRegistrationController;

    public EventViewerViewController() {

        eventRegistrationController = new EventRegistrationViewController();

        eventViewer = new EventViewer();
    }

    public void initializeView(){
        configureWindow();
        setEvents();
        openWindow();
    }

    public void configureWindow(){
        eventViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        eventViewer.setResizable(false);
        eventViewer.setLocationRelativeTo(null);
    }

    @Override
    protected void setEvents() {
        eventViewer.getAddEventButton().addActionListener(actionEvent -> openEventRegistrationWindow());
    }

    @Override
    public void openWindow() {
        eventViewer.setVisible(true);
    }

    private void openEventRegistrationWindow(){
        eventRegistrationController.openWindow();
    }

}
