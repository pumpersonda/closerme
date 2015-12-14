/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Views.EventManagement.EventRegistrationView;
import javax.swing.WindowConstants;

/**
 *
 * @author JoseJulio
 */
public class EventRegistrationViewController extends AbstractViewController {
     private EventRegistrationView eventRegistrationView;
     private EventViewerViewController eventViewerController;
    private final Notifier notifier;

    public EventRegistrationViewController(EventViewerViewController eventViewerController) {
            initializeView();
            this.eventViewerController = eventViewerController;
        notifier = new Notifier();
    }
     
     public void registerEvent(Event newEvent){
         EventManager.getEventManager().reserveEvent(newEvent);
     }
     
     protected void initializeView(){
         configureWindow();
         setEvents();
     }

    private void configureWindow() {
        this.eventRegistrationView = new EventRegistrationView();
        this.eventRegistrationView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.eventRegistrationView.setResizable(false);
        this.eventRegistrationView.setLocationRelativeTo(null);
    }


    protected void setEvents(){
        eventRegistrationView.getCancelButton().addActionListener(actionEvent -> closeWindow());
        eventRegistrationView.getConfirmButton().addActionListener(acionEvent -> processEventCreation());
    }

    private void closeWindow(){
        eventRegistrationView.setVisible(false);
    }

    private void processEventCreation(){
        String eventName = eventRegistrationView.getEventNameTextBox().getText();
        String eventStartDate = eventRegistrationView.getEventStartDateTextBox().getText();
        String eventStartTime = eventRegistrationView.getEventStartTimeTextBox().getText();
        String eventEndDate = eventRegistrationView.getEventEndDateTextBox().getText();
        String eventEndTime = eventRegistrationView.getEventEndTimeTextBox().getText();
        String clientName = eventRegistrationView.getClientNameTextBox().getText();
        String clientPhone = eventRegistrationView.getClientPhoneTextBox().getText();

        boolean isValid = isValid(eventName, eventStartDate, eventStartTime, eventEndDate, eventEndTime, clientName, clientPhone);
        if(isValid) {
            Event newEvent = EventManager.getEventManager().createEvent(eventName, eventStartDate, eventStartTime, eventEndDate, eventEndTime, clientName, clientPhone);
            registerEvent(newEvent);
            eventViewerController.updateView();
            notifier.showSuccessMessage("Evento Creado","El evento se ha creado exitosamente");
            closeWindow();
        }
        else{
            notifier.showWarningMessage("Porfavor llene todos los campos");
        }


    }

    private boolean isValid(String eventName, String eventStartDate, String eventStartTime, String eventEndDate, String eventEndTime, String clientName, String clientPhone) {
        boolean valid = eventName!="" &&
                eventStartDate!="" &&
                eventStartTime!="" &&
                eventEndDate!="" &&
                eventEndTime!="" &&
                clientName!="" &&
                clientPhone!="";
        return  valid;
    }

    @Override
    public void openWindow() {
        this.eventRegistrationView.setVisible(true);
    }


}
