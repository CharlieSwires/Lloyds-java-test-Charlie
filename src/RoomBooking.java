import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class RoomBooking {
    private LocalDate startDate;
    private LocalTime startTime;
    private Integer duration;
    private String employee;
    //2016-07-21 09:00 2

    public RoomBooking(String input) {
        super();
        if (isValid(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            String dateTime = input.substring(0,16);
            this.startTime = LocalTime.parse(dateTime, formatter);
            this.startDate=LocalDate.parse(dateTime, formatter);
            duration = input.charAt(17) - '0';
        } else {
            throw new IllegalArgumentException("bad string:"+input+", expected:"+"2016-07-21 09:00 2");
        }
    }
    protected static boolean isValid(String input) {
        if (input.length() != 18) {
          return false;  
        }
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        String dateTime = input.substring(0,16);
        LocalTime.parse(dateTime, formatter);
        LocalDate.parse(dateTime, formatter);
        } catch (Exception e) {
            return false;
        }
        if (input.charAt(17) < '1' || input.charAt(17) > '8') {
            return false;
        }
        return true;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getEmployee() {
        return employee;
    }
    public void setEmployee(String currentEmployee) {
        this.employee = currentEmployee;
    }
    @Override
    public String toString() {
        return "RoomBooking [startDate=" + startDate + ", startTime=" + startTime + ", duration="
                + duration + ", employee=" + employee + "]";
    }

}
