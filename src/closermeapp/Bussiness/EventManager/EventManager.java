/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Bussiness.EventManager;

        import closermeapp.Bussiness.Entities.Event;
        import closermeapp.Data.DAOs.EventDAO;

        import java.util.ArrayList;

/**
 *
 * @author JoseJulio
 */
public class EventManager {

    public final float COST_PER_HOUR = 20; // pesos

    private EventManager(){
    }
    private static EventManager eventManager;

    public static EventManager getEventManager(){
        if(eventManager == null){
            eventManager = new EventManager();
        }

        return eventManager;
    }

    public Event createEvent(
            String name,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            String clientName,
            String clientPhone
    ){
        String startDateFormated = startDate+","+startTime;
        String endDateFormated = endDate+","+endTime;
        return new Event(name, startDateFormated, endDateFormated, clientName, clientPhone);
    }

    public void reserveEvent(Event event){
        EventDAO.getEventDAO().add(event);
    }

    public void cancelEvent(Event event){
        EventDAO.getEventDAO().delete(event);
    }

    public void updateEvent(Event event){
        EventDAO.getEventDAO().update(event);
    }

    public ArrayList<Event> getAllEvents(){
        return EventDAO.getEventDAO().getList();
    }


}
