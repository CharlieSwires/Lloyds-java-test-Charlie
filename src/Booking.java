import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Booking {
    private static final int LENGTH_OF_EMPLOYEE = 6;

    public Booking(String timeOfBooking, String employee) {
        super();
        //2016-07-18 10:17:06
        if (!Booking.isValid(timeOfBooking, employee)) {
            throw new IllegalArgumentException("Bad strings");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        this.timeOfBooking = LocalTime.parse(timeOfBooking, formatter);
        this.dateOfBooking = LocalDate.parse(timeOfBooking, formatter);
        this.setEmployee(employee);
    }

    private String employee;
    private LocalTime timeOfBooking;
    private LocalDate dateOfBooking;

    public LocalTime getTimeOfBooking() {
        return timeOfBooking;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public static boolean isValid(String timeOfBooking, String employee) {
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalTime.parse(timeOfBooking, formatter);
        LocalDate.parse(timeOfBooking, formatter);
        } catch (Exception e) {
            return false;
        }
        if (employee == null || employee.length() != LENGTH_OF_EMPLOYEE) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Booking [employee=" + employee + ", timeOfBooking=" + timeOfBooking
                + ", dateOfBooking=" + dateOfBooking + "]";
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

}
