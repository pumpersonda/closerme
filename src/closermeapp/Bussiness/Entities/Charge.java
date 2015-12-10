package closermeapp.Bussiness.Entities;

/**
 * Created by JoseJulio on 10/12/2015.
 */
public class Charge {
    private int chargeID;
    private String concept;
    private double amount;

    public Charge(String concept, double amount){
        this.concept = concept;
        this.amount = amount;
    }

    public int getChargeID() {
        return chargeID;
    }

    public void setChargeID(int chargeID) {
        this.chargeID = chargeID;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
