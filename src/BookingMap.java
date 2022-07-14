import java.util.Date;
import java.util.Map;

public class BookingMap {
    private Map<Date,Booking> bookings;

    public Map<Date, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Date, Booking> bookings) {
        this.bookings = bookings;
    }
}
