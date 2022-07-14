import java.util.Date;
import java.util.Map;

public class RoomBookingMap {

    private Map<Date,RoomBooking> room;

    public Map<Date, RoomBooking> getRoom() {
        return room;
    }

    public void setRoom(Map<Date, RoomBooking> room) {
        this.room = room;
    }
    
}
