/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

/**
 *
 * @author JoseJulio
 */
public class Event {

    private int eventID;
    private String name;
    private String startDate;
    private String endDate;
    private String clientName;
    private String clientPhone;

    public Event(){}

    public Event(
            String name, 
            String startDate, 
            String endDate, 
            String clientName,
            String clientPhone
    ) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public int getEventID(){
        return eventID;
    }

    public void setEventID(int eventID){
        this.eventID = eventID;
    }
    
}
