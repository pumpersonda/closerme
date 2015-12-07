package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Views.EventManagement.EventViewer;
import closermeapp.Presentation.Util.TableModel;

import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by JoseJulio on 30/11/2015.
 */
public class EventViewerController extends  AbstractController {

    EventViewer eventViewer;
    EventRegistrationController eventRegistrationController;
    TableModel eventsTableModel;
    private ArrayList<Event> currentEvents;

    public EventViewerController(){

        eventRegistrationController = new EventRegistrationController();

        eventViewer = new EventViewer();
        loadAllEvents();
        initTale();
        initializeView();

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
    protected void openWindow() {
        eventViewer.setVisible(true);
    }

    private void openEventRegistrationWindow(){
        eventRegistrationController.openWindow();
    }

    public void updateView(){
        loadAllEvents();
        updateTable();
    }

    private void initTale(){
        String[] headers = {"", "Event Name", "Starts", "Ends", "ClientName"};
        this.eventsTableModel = new TableModel(headers);
        this.eventViewer.getEventsCalendarTable().setModel(eventsTableModel);
        this.eventViewer.getEventsCalendarTable().getTableHeader().setReorderingAllowed(false);
    }

    public  void loadAllEvents(){
        currentEvents = EventManager.getEventManager().getAllEvents();
    }

    public void updateTable(){
        eventsTableModel.resetTable();
        for(int i=0; i<currentEvents.size() ; i++){
            Event currentEvent = currentEvents.get(i);
            addAsTableRow(currentEvent);
        }
    }

    private void addAsTableRow(Event currentEvent) {
        ArrayList row = getEventRow(currentEvent);
        eventsTableModel.addRow(row);
    }

    private ArrayList getEventRow(Event currentEvent) {
        ArrayList row  = new ArrayList();
        row.add(currentEvent.getName());
        row.add(currentEvent.getStartDate());
        row.add(currentEvent.getEndDate());
        row.add(currentEvent.getClient().getName());
        return row;
    }

}
