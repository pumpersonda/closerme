package closermeapp.Bussiness.Entities;

import java.io.Serializable;

/**
 * Created by André on 29/11/2015.
 */
public class CallLog implements Serializable {
    private String memberName;
    private int registerId;
    private String date;
    private String numberPhone;
    private String duration;

    public CallLog() {
    }

    public CallLog(
            String memberName,
            String numberPhone,
            String duration
    ) {
        this.memberName = memberName;
        this.duration = duration;
        this.numberPhone = numberPhone;

    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getRegisterId() {
        return registerId;
    }
}
