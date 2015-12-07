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

    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private Client client;
    
    public Event(
            String name, 
            String startDate, 
            String endDate, 
            String visitorName, 
            String visitorPhone
    ) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        client = new Client(visitorName, visitorPhone);
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
    
    public Client getClient(){
       return this.client;
    }
    
    public void setVisitor(String name, String phone){
        this.client.setName(name);
        this.client.setPhone(phone);
    }

    public int getId(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }
    
}
