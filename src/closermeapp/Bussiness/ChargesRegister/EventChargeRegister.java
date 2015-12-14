package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Event;

/**
 * Created by JoseJulio on 13/12/2015.
 */
public class EventChargeRegister {

    int eventRegisterId;
    String eventName;
    String eventStartDate;
    String eventEndDate;
    String clientName;
    String clientPhone;
    double totalCost;


    public EventChargeRegister(Event event, double totalCost) {
        eventRegisterId = event.getEventID();
        eventName = event.getName();
        eventStartDate = event.getStartDate();
        eventEndDate = event.getEndDate();
        clientName = event.getClientName();
        clientPhone = event.getClientPhone();
        this.totalCost = totalCost;
    }

    public EventChargeRegister() {
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getEventRegisterId() {
        return eventRegisterId;
    }

    public void setEventRegisterId(int eventRegisterId) {
        this.eventRegisterId = eventRegisterId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    @Override
    public String toString() {
        return "EventChargeRegister{" +
                "eventRegisterId=" + eventRegisterId +
                ", eventName='" + eventName + '\'' +
                ", eventStartDate='" + eventStartDate + '\'' +
                ", eventEndDate='" + eventEndDate + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                '}';
    }
}
