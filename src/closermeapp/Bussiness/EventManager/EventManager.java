/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.EventManager;

import closermeapp.Bussiness.Entities.Event;

/**
 *
 * @author JoseJulio
 */
public class EventManager {

    private EventManager(){ }
    private static EventManager eventManager;

    public static EventManager getEventManager(){
        if(eventManager == null){
            eventManager = new EventManager();
        }

        return eventManager;
    }

    public Event createEvent(String name, String startDate,String startTime, String endDate,String endTime, String clientName, String clientPhone){
         String startDateFormated = startDate+startTime;
         String endDateFormated = endDate+endTime;
         Event newEvent = new Event(name, startDateFormated, endDateFormated, clientName, clientPhone);
         return newEvent;
    }
    
    public void addEvent(Event event){
        //
    }

    public void deleteEvent(Event event){

    }

    public void changeEventStartDate(int EventID, String newStartDate, String newStartTime){

    }

    public void changeEventEndDate(int EventID, String newEndDate, String newEndTime){

    }

}   
