
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Presentation.Controllers.Event;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Views.EventManagement.EventRegistrationView;

import javax.swing.*;

/**
 *
 * @author JoseJulio
 */
public class EventRegistrationViewController extends AbstractViewController {
    private EventRegistrationView eventRegistrationView;
    private EventMenuController eventViewerController;

    public EventRegistrationViewController(EventMenuController eventViewerController) {
        initializeView();
        this.setEventViewerController(eventViewerController);
    }

    public void registerEvent(Event newEvent){
        EventManager.getEventManager().reserveEvent(newEvent);
    }

    protected void initializeView(){
        configureWindow();
        setEvents();
    }

    private void configureWindow() {
        this.setEventRegistrationView(new EventRegistrationView());
        this.getEventRegistrationView().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getEventRegistrationView().setResizable(false);
        this.getEventRegistrationView().setLocationRelativeTo(null);
    }


    protected void setEvents(){
        getEventRegistrationView().getCancelButton().addActionListener(actionEvent -> closeWindow());
        getEventRegistrationView().getConfirmButton().addActionListener(acionEvent -> processEventCreation());
    }

    private void closeWindow(){
        getEventRegistrationView().setVisible(false);
    }

    private void processEventCreation(){
        String eventName = getEventRegistrationView().getEventNameTextBox().getText();
        String eventStartDate = getEventRegistrationView().getEventStartDateTextBox().getText();
        String eventStartTime = getEventRegistrationView().getEventStartTimeTextBox().getText();
        String eventEndDate = getEventRegistrationView().getEventEndDateTextBox().getText();
        String eventEndTime = getEventRegistrationView().getEventEndTimeTextBox().getText();
        String clientName = getEventRegistrationView().getClientNameTextBox().getText();
        String clientPhone = getEventRegistrationView().getClientPhoneTextBox().getText();

        Event newEvent = createEvent(
                eventName,
                eventStartDate,
                eventStartTime,
                eventEndDate,
                eventEndTime,
                clientName,
                clientPhone
        );

        boolean isValid = isValid(newEvent);
        if(isValid) {

            saveEvent(newEvent);
            getNotifier().showSuccessMessage("Evento Creado", "El evento se ha creado exitosamente");
            closeWindow();
        }
        else{
            getNotifier().showWarningMessage("Porfavor llene todos los campos");
        }
    }

    private void saveEvent(Event newEvent) {
        registerEvent(newEvent);
        getEventViewerController().updateView();
    }

    private Event createEvent(
            String eventName,
            String eventStartDate,
            String eventStartTime,
            String eventEndDate,
            String eventEndTime,
            String clientName,
            String clientPhone
    )
    {
        return EventManager.getEventManager().createEvent(
                eventName,
                eventStartDate,
                eventStartTime,
                eventEndDate,
                eventEndTime,
                clientName,
                clientPhone
        );
    }

    private boolean isValid(Event event) {
        boolean valid = event.getName()!="" &&
                event.getStartDate()!="" &&
                event.getEndDate()!="" &&
                event.getClientName()!="" &&
                event.getClientPhone()!="";
        return  valid;
    }

    @Override
    public void openWindow() {
        this.getEventRegistrationView().setVisible(true);
    }


    public EventRegistrationView getEventRegistrationView() {
        return eventRegistrationView;
    }

    public void setEventRegistrationView(EventRegistrationView eventRegistrationView) {
        this.eventRegistrationView = eventRegistrationView;
    }

    public EventMenuController getEventViewerController() {
        return eventViewerController;
    }

    public void setEventViewerController(EventMenuController eventViewerController) {
        this.eventViewerController = eventViewerController;
    }
}
