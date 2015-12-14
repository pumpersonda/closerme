package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Cashier.Cashier;
import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.EventManager.EventManager;
import closermeapp.Presentation.Controllers.Charge.ChargeController;
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
public class EventMenuController extends  AbstractViewController {

    private EventViewer eventViewer;
    private EventRegistrationViewController eventRegistrationController;
    private ChargeController chargeController;
    private TableModel eventsTableModel;
    private ArrayList<Event> currentEvents;

    public EventMenuController(){

        initializeFields();
        loadAllEvents();
        initTale();
        initializeView();

    }

    private void initializeFields() {
        setEventRegistrationController(new EventRegistrationViewController(this));
        setChargeController(new ChargeController());
        setEventViewer(new EventViewer());
    }

    public void initializeView(){
        configureWindow();
        setEvents();
        openWindow();
    }

    public void configureWindow(){
        getEventViewer().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getEventViewer().setResizable(false);
        getEventViewer().setLocationRelativeTo(null);
    }

    @Override
    protected void setEvents() {
        getEventViewer().getAddEventButton().addActionListener(actionEvent -> openEventRegistrationWindow());
        getEventViewer().getEditButton().addActionListener(actionEvent -> prepareEditionWindow());
        getEventViewer().getDeleteButton().addActionListener(actionEvent -> deleteEvent());
    }

    @Override
    public void openWindow() {
        getEventViewer().setVisible(true);
    }

    private void openEventRegistrationWindow(){
        getEventRegistrationController().openWindow();
    }

    private void prepareEditionWindow(){
        Event seletedEvent = getSelectedEvent();
        if(seletedEvent == null){
            getNotifier().showWarningMessage("No se ha seleccionado ningun evento");
        }else {
            openEventEditionWindow(seletedEvent);
        }
    }

    private void openEventEditionWindow(Event seletedEvent) {
        EventEditionController eventEditionController = new EventEditionController(this,seletedEvent);
        eventEditionController.openWindow();
    }

    private Event getSelectedEvent(){
        try {
            int selectedEventIndex = getEventViewer().getEventsCalendarTable().getSelectedRow();
            Event selectedEvent = getCurrentEvents().get(selectedEventIndex);
            return  selectedEvent;
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void updateView(){
        loadAllEvents();
        loadEventsToTable();
    }

    private void initTale(){
        String[] headers = {"", "Event Name", "Starts", "Ends", "ClientName"};

        configureTable(headers);

        TableColumnModel columnModel = getEventViewer().getEventsCalendarTable().getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn(firstColumn).setPreferredWidth(sizeColumn);

        updateView();
    }

    private void configureTable(String[] headers) {
        this.setEventsTableModel(new TableModel(headers));
        getEventsTableModel().resetTable();
        this.getEventViewer().getEventsCalendarTable().setModel(getEventsTableModel());
        this.getEventViewer().getEventsCalendarTable().getTableHeader().setReorderingAllowed(false);
    }

    public  void loadAllEvents(){
        setCurrentEvents(EventManager.getEventManager().getAllEvents());
    }

    private void loadEventsToTable() {
        resetTable();
        for (int listIndex = 0; listIndex < getCurrentEvents().size(); listIndex++) {
            Event event = getCurrentEvents().get(listIndex);
            ArrayList memberDataList = createRow(event, listIndex);
            addRow(memberDataList);
        }
    }

    private void addRow(ArrayList list) {
        getEventsTableModel().addRow(list);
    }

    private void resetTable() {
        getEventsTableModel().resetTable();
    }

    private ArrayList<String> createRow(Event event, int listIndex) {
        ArrayList<String> row = new ArrayList();

        int tablePosition = listIndex + 1;
        row.add(valueOf(tablePosition));
        row.add(event.getName());
        row.add(event.getStartDate());
        row.add(event.getEndDate());
        row.add(event.getClientName());

        return row;
    }

    private void deleteEvent() {
        Event event = getSelectedEvent();
        if (event == null) {
            Notifier notifier = new Notifier();
            notifier.showWarningMessage("No se ha sekeccionado ningun evento");
        }
        else{


            if(confirmIfEventSucceded()){
                double totalCost = Cashier.getInstance().chargeThEvent(event);
                getChargeController().setTotalChargeMessage(totalCost);
                getChargeController().openWindow();

                removeEvent(event);

            }
            else {
                if(confirmEventDeletion()) {
                    removeEvent(event);
                }
            }
        }
    }

    private void removeEvent(Event event) {
        EventManager.getEventManager().cancelEvent(event);
        getCurrentEvents().remove(event);
        updateView();
    }

    private  boolean confirmIfEventSucceded(){
        Notifier notifier = new Notifier();
        int answer = notifier.showConfirmDialog("�El evento concluyo con exito?");
        return answer == notifier.getYES_OPTION();
    }

    private boolean confirmEventDeletion(){
        Notifier notifier = new Notifier();
        int answer = notifier.showConfirmDialog("�Estas seguro que deseas eliminar este evento?");
        return answer == notifier.getYES_OPTION();
    }

    private EventViewer getEventViewer() {
        return eventViewer;
    }

    private void setEventViewer(EventViewer eventViewer) {
        this.eventViewer = eventViewer;
    }

    private EventRegistrationViewController getEventRegistrationController() {
        return eventRegistrationController;
    }

    private void setEventRegistrationController(EventRegistrationViewController eventRegistrationController) {
        this.eventRegistrationController = eventRegistrationController;
    }

    private ChargeController getChargeController() {
        return chargeController;
    }

    private void setChargeController(ChargeController chargeController) {
        this.chargeController = chargeController;
    }

    private TableModel getEventsTableModel() {
        return eventsTableModel;
    }

    private void setEventsTableModel(TableModel eventsTableModel) {
        this.eventsTableModel = eventsTableModel;
    }

    private ArrayList<Event> getCurrentEvents() {
        return currentEvents;
    }

    private void setCurrentEvents(ArrayList<Event> currentEvents) {
        this.currentEvents = currentEvents;
    }
}