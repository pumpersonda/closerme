package closermeapp.Data.DAOs;

import closermeapp.Bussiness.ChargesRegister.EventChargeRegister;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseJulio on 13/12/2015.
 */
public class EventChargeRegisterDAO extends AbstractDAO<EventChargeRegister> {

    private static EventChargeRegisterDAO eventChargeRegisterDAO;

    private EventChargeRegisterDAO() {

    }

    public static EventChargeRegisterDAO getEventChargeRegisterDAO() {
        if (eventChargeRegisterDAO == null) {
            eventChargeRegisterDAO = new EventChargeRegisterDAO();
        }
        return eventChargeRegisterDAO;
    }

    @Override
    public void add(EventChargeRegister eventChargeRegister) {
        saveEntity( eventChargeRegister );
    }

    @Override
    public void delete(EventChargeRegister eventChargeRegister) {
        deleteEntity( eventChargeRegister );
    }

    @Override
    public void update(EventChargeRegister eventChargeRegister) {
        updateEntity( eventChargeRegister );
    }

    @Override
    public Object get(int objectId) {
        EventChargeRegister member = null;

        try {
            openSession();

            member = (EventChargeRegister) session.get( EventChargeRegister.class, objectId );
        } finally {
            session.close();
        }
        return member;
    }

    @Override
    public ArrayList<?> getList() {
        ArrayList memberList = null;

        try {
            openSession();

            memberList = (ArrayList) session.createQuery( "from EventChargeRegister " ).list();
        } finally {
            session.close();
        }

        return memberList;
    }

    public List<EventChargeRegister> getInSpecifiedDate(String startDate, String endDate) {

        List membershipList = null;

        try {
            openSession();

            Criteria criteria = session.createCriteria( EventChargeRegister.class );
            criteria.add( Restrictions.ge( "eventStartDate", startDate ) );
            criteria.add( Restrictions.lt( "eventStartDate", endDate ) );
            membershipList = criteria.list();

        } finally {
            session.close();
        }

        return membershipList;

    }


}
