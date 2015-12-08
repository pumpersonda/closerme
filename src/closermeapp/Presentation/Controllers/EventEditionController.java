package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Views.EventManagement.EventEditionView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by JoseJulio on 07/12/2015.
 */
public class EventEditionController {

    EventEditionView eventEditionView;
    Event eventToEdit;

    public EventEditionController(Event eventToEdit){

        eventEditionView = new EventEditionView();
        initializeView();

        this.eventToEdit = eventToEdit;

    }

    public void initializeView(){
        configureWindow();
        setEvents();
        openWindow();
    }

    private void openWindow() {
        eventEditionView.setVisible(true);
    }

    private void setEvents() {
       eventEditionView.getConfirmButton().addActionListener(ActionEvent -> editEvent());
       eventEditionView.getCancelButton().addActionListener(ActionEvent -> closeWindow());
    }

    private void configureWindow() {
        eventEditionView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        eventEditionView.setResizable(false);
        eventEditionView.setLocationRelativeTo(null);
    }

    private void editEvent(){

        String newName = eventEditionView.getEventNameTextBox().getText();
        String newStartDate = eventEditionView.getEventStartDateTextBox().getText();
        String newStartTime = eventEditionView.getEventStartTimeTextBox().getText();
        String newEndDate = eventEditionView.getEventEndDateTextBox().getText();
        String newEndTime = eventEditionView.getEventEndTimeTextBox().getText();

        eventToEdit.setName(newName);
        eventToEdit.setStartDate(newStartDate + newStartTime);
        eventToEdit.setEndDate(newEndDate + newEndTime);

        EventManager.getEventManager().updateEvent(eventToEdit);

        closeWindow();
    }

    private void closeWindow(){
        eventEditionView.setVisible(false);
    }

}
