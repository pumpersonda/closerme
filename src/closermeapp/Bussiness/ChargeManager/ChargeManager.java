package closermeapp.Bussiness.ChargeManager;

import closermeapp.Bussiness.Entities.Charge;
import closermeapp.Data.DAOs.ChargeDAO;
import closermeapp.Presentation.Util.Notifier;

import java.util.ArrayList;

/**
 * Created by JoseJulio on 10/12/2015.
 */
public class ChargeManager {

    private static ChargeManager chargeManager;
    public static ChargeManager getChargeManager(){
        if(chargeManager == null){
            chargeManager = new ChargeManager();
        }
        return chargeManager;
    }

    private ChargeManager(){}

    private Charge createChargeObect(String concept, double amount){
        return new Charge(concept, amount);
    }

    public void charge(String concept, double amount){
        Notifier notifier = new Notifier();
        int answer = notifier.showConfirmDialog("Cobrar $"+amount);

        if(answer == notifier.getYES_OPTION()){
            Charge newCharge = createChargeObect(concept, amount);
            ChargeDAO.GetInstance().add( newCharge );
        }
    }

    public ArrayList<Charge> getChargeReport(){
        ArrayList<Charge> charges = ChargeDAO.GetInstance().getList();
        return charges;
    }

}
