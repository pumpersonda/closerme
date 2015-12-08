/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.EventManager;

import closermeapp.Bussiness.Entities.Event;
import closermeapp.Data.DAOs.EventDAO;
import closermeapp.Presentation.Controllers.EventViewerController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author JoseJulio
 */
public class EventManager {

    private final float COST_PER_HOUR = 20; // pesos

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

    public void reserveEvent(Event event){
        EventDAO.getEventDAO().add(event);
        eventViewerController.updateView();
    }

    public void cancelEvent(Event event){
        EventDAO.getEventDAO().delete(event);
        eventViewerController.updateView();
    }

    public void chargeForEvent(Event event){
        double finalCost = calculateEventCost(event.getStartDate(), event.getEndDate());
        //chargeManager.chargeForEvent(finalCost);
        cancelEvent(event);
    }

    public void updateEvent(Event event){
        EventDAO.getEventDAO().update(event);
    }

    public ArrayList<Event> getAllEvents(){
        return EventDAO.getEventDAO().getList();
    }

    private double calculateEventCost(String startDate, String endDate){
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);

        long hours = ChronoUnit.HOURS.between(startDateTime, endDateTime);

        double totalCost = (float)hours * COST_PER_HOUR;

        return totalCost;
    }

}   
