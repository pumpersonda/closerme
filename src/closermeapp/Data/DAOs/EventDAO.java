package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Event;

import java.util.ArrayList;

/**
 * Created by JoseJulio on 01/12/2015.
 */
public class EventDAO extends AbstractDAO<Event> {

    private static EventDAO eventDAO;

    private EventDAO(){}

    public static EventDAO getEventDAO(){
        if(eventDAO == null){
            eventDAO = new EventDAO();
        }
        return eventDAO;
    }

    @Override
    public void add(Event event) {
        saveEntity(event);
    }

    @Override
    public void delete(Event event) {
        deleteEntity(event);
    }

    @Override
    public void update(Event event) {
        updateEntity(event);
    }

    @Override
    public Object get(int objectId) {
        Event event = null;
        try{
            openSession();

            event = (Event)session.get(Event.class,objectId);
        }finally {
            session.close();
        }
        return event;
    }

    @Override
    public ArrayList<Event> getList() {
        ArrayList eventList = null;

        try{
            openSession();

            eventList = (ArrayList<Event>)session.createQuery("FROM Event").list();

        }finally {
            session.close();
        }
        return eventList;
    }
}
