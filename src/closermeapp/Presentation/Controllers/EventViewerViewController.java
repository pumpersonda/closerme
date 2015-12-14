package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Cashier.Cashier;
import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.EventManagement.EventViewer;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.String.valueOf;


/**
 * Created by JoseJulio on 30/11/2015.
 */

public class EventViewerViewController extends AbstractViewController {

    EventViewer eventViewer;
    EventRegistrationViewController eventRegistrationController;
    ChargeController chargeController;
    TableModel eventsTableModel;
    private ArrayList<Event> currentEvents;


    public EventViewerViewController() {

        eventRegistrationController = new EventRegistrationViewController(this);
        chargeController = new ChargeController();


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
        eventViewer.getEditButton().addActionListener( actionEvent -> openEventEditionWindow() );
        eventViewer.getDeleteButton().addActionListener( actionEvent -> deleteEvent() );
    }

    @Override
    public void openWindow() {
        eventViewer.setVisible(true);
    }

    private void openEventRegistrationWindow(){
        eventRegistrationController.openWindow();
    }

    private void openEventEditionWindow() {

        Event seletedEvent = getSelectedEvent();
        if (seletedEvent == null) {
            Notifier notifier = new Notifier();
            notifier.showWarningMessage( "No se ha seleccionado ningun evento" );
        } else {
            EventEditionController eventEditionController = new EventEditionController( this, seletedEvent );
            eventEditionController.openWindow();
            updateView();
        }
    }

    private Event getSelectedEvent() {
        int selectedEventIndex = eventViewer.getEventsCalendarTable().getSelectedRow();

        Event selectedEvent = currentEvents.get( selectedEventIndex );

        return selectedEvent;

    }

    public void updateView(){
        loadAllEvents();
        loadEventsToTable();
    }

    private void initTale(){
        String[] headers = {"", "Event Name", "Starts", "Ends", "ClientName"};
        this.eventsTableModel = new TableModel(headers);
        eventsTableModel.resetTable();
        this.eventViewer.getEventsCalendarTable().setModel(eventsTableModel);
        this.eventViewer.getEventsCalendarTable().getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = eventViewer.getEventsCalendarTable().getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn( firstColumn ).setPreferredWidth( sizeColumn );

        updateView();
    }

    public  void loadAllEvents(){
        currentEvents = EventManager.getEventManager().getAllEvents();
    }

    private void loadEventsToTable() {
        resetTable();
        for (int listIndex = 0; listIndex < currentEvents.size(); listIndex++) {
            Event event = currentEvents.get( listIndex );
            ArrayList memberDataList = createRow( event, listIndex );
            addRow( memberDataList );
        }
    }

    private void addRow(ArrayList list) {
        eventsTableModel.addRow( list );
    }

    private void resetTable() {
        eventsTableModel.resetTable();
    }

    private ArrayList<String> createRow(Event event, int listIndex) {
        ArrayList<String> row = new ArrayList();

        int tablePosition = listIndex + 1;
        row.add( valueOf( tablePosition ) );
        row.add( event.getName() );
        row.add( event.getStartDate() );
        row.add( event.getEndDate() );
        row.add( event.getClientName() );

        return row;
    }

    private void deleteEvent() {
        Event event = getSelectedEvent();
        if (event == null) {
            Notifier notifier = new Notifier();
            notifier.showWarningMessage( "No se ha sekeccionado ningun evento" );
        } else {


            if (confirmIfEventSucceded()) {
                double totalCost = Cashier.getInstance().chargeThEvent( event );
                chargeController.setTotalChargeMessage( totalCost );
                chargeController.openWindow();

                removeEvent( event );

            } else {
                if (confirmEventDeletion()) {
                    removeEvent( event );
                }
            }
        }
    }

    private void removeEvent(Event event) {
        EventManager.getEventManager().cancelEvent( event );
        currentEvents.remove( event );
        updateView();
    }

    private boolean confirmIfEventSucceded() {
        Notifier notifier = new Notifier();
        int answer = notifier.showConfirmDialog( "�El evento concluyo con exito?" );
        return answer == notifier.getYES_OPTION();
    }

    private boolean confirmEventDeletion() {
        Notifier notifier = new Notifier();
        int answer = notifier.showConfirmDialog( "�Estas seguro que deseas eliminar este evento?" );
        return answer == notifier.getYES_OPTION();
    }

}
