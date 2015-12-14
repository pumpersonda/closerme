package closermeapp.Data.DAOs;

import closermeapp.Bussiness.Entities.Charge;

import java.util.ArrayList;

/**
 * Created by JoseJulio on 10/12/2015.
 */
public class ChargeDAO extends AbstractDAO<Charge>{

    private  static  ChargeDAO chargeDAO;

    private ChargeDAO(){

    }

    public static ChargeDAO getChargeDAO(){
        if(chargeDAO== null){
            chargeDAO = new ChargeDAO();
        }
        return chargeDAO;
    }

    @Override
    public void add(Charge charge) {
        saveEntity(charge);
    }

    @Override
    public void delete(Charge charge) {
        deleteEntity(charge);
    }

    @Override
    public void update(Charge charge) {
        updateEntity(charge);
    }

    @Override
    public Object get(int objectId) {
        Charge charge = null;
        try{
            openSession();

            charge = (Charge)session.get(Charge.class,objectId);
        }finally {
            session.close();
        }
        return charge;
    }

    @Override
    public ArrayList<Charge> getList() {
        ArrayList chargeList = null;

        try{
            openSession();

            chargeList = (ArrayList<Charge>)session.createQuery("FROM Charge").list();

        }finally {
            session.close();
        }
        return chargeList;
    }
}
