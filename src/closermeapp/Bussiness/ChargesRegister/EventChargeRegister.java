package closermeapp.Bussiness.ChargesRegister;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.Util.DateFormater;

/**
 * Created by JoseJulio on 13/12/2015.
 */
public class EventChargeRegister {

    private int eventRegisterId;
    private String eventName;
    private String eventStartDate;
    private String clientName;
    private String clientPhone;
    private double totalCost;


    public  EventChargeRegister(Event event, double totalCost){
        setEventRegisterId(event.getEventID());
        setEventName(event.getName());
        setEventStartDate(DateFormater.formatDateYYYYMMDD(event.getStartDate()));
        setClientName(event.getClientName());
        setClientPhone(event.getClientPhone());
        this.setTotalCost(totalCost);
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
                "eventRegisterId=" + getEventRegisterId() +
                ", eventName='" + getEventName() + '\'' +
                ", eventStartDate='" + getEventStartDate() + '\'' +
                ", clientName='" + getClientName() + '\'' +
                ", clientPhone='" + getClientPhone() + '\'' +
                '}';
    }
}
