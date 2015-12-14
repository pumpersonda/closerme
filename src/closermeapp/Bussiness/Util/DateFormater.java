package closermeapp.Bussiness.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by JoseJulio on 14/12/2015.
 */
public class DateFormater {

    public static LocalDate getParsedDate(String date) {
        String[] dateValues = date.split( "/" );

        int day = Integer.parseInt( dateValues[0] );
        int month = Integer.parseInt( dateValues[1] );
        int year = Integer.parseInt( dateValues[2] );

        LocalDate parsedDate = LocalDate.of( day, month, year );
        return parsedDate;
    }

    public static LocalTime getParsedTime(String time) {
        String[] timeValues = time.split( " " );
        String hours = timeValues[0].substring( 0, 2 );
        String minutes = timeValues[0].substring( 3, 5 );

        int numeritHour = Integer.parseInt( hours );
        int numericMinutes = Integer.parseInt( minutes );

        if (timeValues[1] == "PM") {
            numeritHour = (numeritHour + 12) % 24;
        }

        LocalTime parsedTime = LocalTime.of( numeritHour, numericMinutes );

        return parsedTime;
    }

    public static String formatDateYYYYMMDD(String dateTime){
        String date = dateTime.split(",")[0];
        String[] values = date.split("/");
        String day = values[0];
        String month = values[1];
        String year = "20" + values[2];

        return year+"-"+month+"-"+day;
    }

    public static LocalDateTime getParsedDateTime(String date){
        String[] splitedDate = date.split(",");
        LocalDate localDate = DateFormater.getParsedDate(splitedDate[0]);
        LocalTime localTime = DateFormater.getParsedTime(splitedDate[1]);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return localDateTime;
    }

}
