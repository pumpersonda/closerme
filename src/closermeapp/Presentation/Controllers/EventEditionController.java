package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Views.EventManagement.EventEditionView;

import javax.swing.*;

/**
 * Created by JoseJulio on 07/12/2015.
 */
public class EventEditionController extends AbstractViewController {

    EventEditionView eventEditionView;
    Event eventToEdit;
    EventViewerViewController eventViewerViewController;

    public EventEditionController(EventViewerViewController eventViewerViewController, Event eventToEdit) {

        this.eventToEdit = eventToEdit;
        this.eventViewerViewController = eventViewerViewController;

        eventEditionView = new EventEditionView();
        initializeView();
    }

    public void initializeView(){
        configureWindow();
        setEvents();
        setFieldsValue();
        openWindow();
    }

    public void openWindow() {
        eventEditionView.setVisible(true);
    }

    protected void setEvents() {
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
        eventToEdit.setStartDate( newStartDate + "," + newStartTime );
        eventToEdit.setEndDate( newEndDate + "," + newEndTime );

        EventManager.getEventManager().updateEvent(eventToEdit);

        eventViewerViewController.updateView();

        closeWindow();
    }

    private void closeWindow(){
        eventEditionView.setVisible(false);
    }

    private void setFieldsValue() {

        String[] startTimeValues = eventToEdit.getStartDate().split( "," );
        String[] endTimeValues = eventToEdit.getEndDate().split( "," );

        eventEditionView.getEventNameTextBox().setText( eventToEdit.getName() );
        eventEditionView.getEventStartDateTextBox().setText( startTimeValues[0] );
        eventEditionView.getEventStartTimeTextBox().setText( startTimeValues[1] );
        eventEditionView.getEventEndDateTextBox().setText( endTimeValues[0] );
        eventEditionView.getEventEndTimeTextBox().setText( endTimeValues[1] );
    }

}
