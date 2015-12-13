/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.EventManager;

import closermeapp.Bussiness.ChargeManager.ChargeManager;
import closermeapp.Bussiness.Entities.Event;
import closermeapp.Data.DAOs.EventDAO;
import closermeapp.Presentation.Controllers.EventViewerController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
         String startDateFormated = startDate+"|"+startTime;
         String endDateFormated = endDate+"|"+endTime;
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

        ChargeManager.getChargeManager().charge("Evento: "+event.getName(), finalCost);

        cancelEvent(event);
    }

    public void updateEvent(Event event){
        EventDAO.getEventDAO().update(event);
    }

    public ArrayList<Event> getAllEvents(){
        return EventDAO.getEventDAO().getList();
    }

    private double calculateEventCost(String start, String end){

        String[] splitedStart = start.split("|");
        LocalDate startDate = LocalDate.parse(splitedStart[0]);
        LocalTime startTime = LocalTime.parse(splitedStart[1]);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        String[] splitedEnd = end.split("|");
        LocalDate endDate = LocalDate.parse(splitedEnd[0]);
        LocalTime endTime = LocalTime.parse(splitedEnd[1]);
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        long hours = ChronoUnit.HOURS.between(startDateTime, endDateTime);

        double totalCost = (float)hours * COST_PER_HOUR;

        return totalCost;
    }

}   
