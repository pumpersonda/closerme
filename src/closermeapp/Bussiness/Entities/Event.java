/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.Entities;

import java.time.LocalDateTime;

/**
 *
 * @author JoseJulio
 */
public class Event {
    
    private String name;
    private String startDate;
    private String endDate;
    private Visitor visitor;
    
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
        visitor = new Visitor(visitorName, visitorPhone);  
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
    
    public Visitor getVisitor(){
       return this.visitor;
    }
    
    public void setVisitor(String name, String phone){
        this.visitor.setName(name);
        this.visitor.setPhone(phone);
    }
    
}
