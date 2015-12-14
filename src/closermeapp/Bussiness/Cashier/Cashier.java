package closermeapp.Bussiness.Cashier;

import closermeapp.Bussiness.ChargesRegister.ChargesRegisterGenerator;
import closermeapp.Bussiness.Entities.Enterprise;
import closermeapp.Bussiness.Entities.Event;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.EventManager.EventManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by André on 12/12/2015.
 */
public class Cashier {
    private static final Cashier cashier = new Cashier();
    private ChargesRegisterGenerator chargesRegisterGenerator;


    private Cashier() {
        this.chargesRegisterGenerator = new ChargesRegisterGenerator();
    }

    public static Cashier getInstance() {
        return cashier;
    }

    public double chargeTheMember(Member member, double discount) {
        double membershipCost = member.getMembership().getCosts();
        double totalCost = membershipCost - discount;

        getChargesRegisterGenerator().addMemberChargesRegister(member, totalCost);
        return totalCost;
    }

    public double chargeTheEnterprise(Enterprise enterprise) {
        double membershipCost = enterprise.getMembership().getCosts();
        int employeesNumber = enterprise.getEmployeeList().size();
        double totalCost = membershipCost * employeesNumber;
        getChargesRegisterGenerator().addEnterpriseChargeRegister(enterprise, totalCost);
        return totalCost;
    }

    public double chargeThEvent(Event event) {

        String start = event.getStartDate();
        String end = event.getEndDate();

        String[] splitedStart = start.split(",");
        LocalDate startDate = getParsedDate(splitedStart[0]);
        LocalTime startTime = getParsedTime(splitedStart[1]);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        String[] splitedEnd = end.split(",");
        LocalDate endDate = getParsedDate(splitedEnd[0]);
        LocalTime endTime = getParsedTime(splitedEnd[1]);
        LocalDateTime endDateTime = LocalDateTime.of(endDate,endTime);

        long hours = ChronoUnit.HOURS.between(startDateTime, endDateTime);

        double totalCost = (float)hours * EventManager.getEventManager().COST_PER_HOUR;

        totalCost = Math.abs(totalCost);

        return totalCost;
    }

    private LocalDate getParsedDate(String date){
        String[] dateValues = date.split("/");

        int day = Integer.parseInt(dateValues[0]);
        int month = Integer.parseInt(dateValues[1]);
        int year = Integer.parseInt(dateValues[2]);

        LocalDate parsedDate = LocalDate.of(day,month,year);
        return parsedDate;
    }

    private LocalTime getParsedTime(String time){
        String[] timeValues = time.split(" ");
        String hours = timeValues[0].substring(0,2);
        String minutes = timeValues[0].substring(3, 5);

        int numeritHour = Integer.parseInt(hours);
        int numericMinutes = Integer.parseInt(minutes);

        if(timeValues[1] == "PM"){
            numeritHour = (numeritHour+12)%24;
        }

        LocalTime parsedTime = LocalTime.of(numeritHour,numericMinutes);

        return parsedTime;
    }

    public ChargesRegisterGenerator getChargesRegisterGenerator() {
        return chargesRegisterGenerator;
    }

}
