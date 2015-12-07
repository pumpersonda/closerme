/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.EventManager;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Data.DAOs.EventDAO;
import closermeapp.Presentation.Controllers.EventViewerController;

import java.util.ArrayList;

/**
 *
 * @author JoseJulio
 */
public class EventManager {

    private EventViewerController eventViewerController;

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
        EventDAO.getEventDAO().add(event);
        eventViewerController.updateView();
    }

    public void deleteEvent(Event event){
        EventDAO.getEventDAO().delete(event);
        eventViewerController.updateView();
    }

    /*
    public void changeEventStartDate(int EventID, String newStartDate, String newStartTime){
        Event event =  (Event)EventDAO.getEventDAO().get(EventID);
        String starDateFormated = newStartDate + newStartTime;
        event.setStartDate(newStartDate);
        EventDAO.getEventDAO().update(event);
    }

    public void changeEventEndDate(int EventID, String newEndDate, String newEndTime){
        String endDateFormated = newEndDate + newEndTime;
        Event event = (Event)EventDAO.getEventDAO().get(EventID);
        event.setEndDate(endDateFormated);
        EventDAO.getEventDAO().update(event);
    }
    */

    public void updateEvent(Event event){
        EventDAO.getEventDAO().update(event);
    }

    public ArrayList<Event> getAllEvents(){
        return EventDAO.getEventDAO().getList();
    }

}   
