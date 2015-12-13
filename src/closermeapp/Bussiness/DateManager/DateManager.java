package closermeapp.Bussiness.DateManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by André on 12/12/2015.
 */
public class DateManager {
    private static DateManager dateManager;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    private DateManager() {

    }

    public static DateManager getInstance() {
        if (dateManager == null) {
            dateManager = new DateManager();
        }
        return dateManager;
    }


    public String getTodayDate() {
        String today = LocalDateTime.now().format(formatter);
        return today;
    }

    public String getFutureDate(int days) {
        String date = LocalDateTime.now().plusDays(days).format(formatter);
        return date;
    }

    public String getPastDate(int days) {
        String date = LocalDateTime.now().minusDays(days).format(formatter);
        return date;
    }

    public String getDate(int month) {
        String day = "01";
        int year = LocalDateTime.now().getYear();
        String date = year + "-" + month + "-" + day;

        return date;
    }

    public String getNextDate(int month) {

        String day = "01";
        String nextMonth = getNextMonth(month);
        int year = getYear(month);


        String date = year + "-" + nextMonth + "-" + day;
        return date;
    }

    public String getNextMonth(int month) {
        String nextMonth;
        boolean isDecember = month == 12;
        if (isDecember) {
            nextMonth = "01";
        } else {
            month++;
            nextMonth = String.valueOf(month);
        }

        return nextMonth;
    }


    private int getYear(int month) {
        int year = LocalDateTime.now().getYear();
        if (month == 12) {
            year++;
        }
        return year;
    }


}
