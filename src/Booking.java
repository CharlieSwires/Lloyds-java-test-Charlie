import java.util.Date;

public class Booking {
    private DayTime startTime24h;
    private DayTime stopTime24h;
    private Date timeOfBooking;
    private RoomBooking roomBooking;



    public DayTime getStartTime24h() {
        return startTime24h;
    }

    public void setStartTime24h(DayTime startTime24h) {
        this.startTime24h = startTime24h;
    }

    public DayTime getStopTime24h() {
        return stopTime24h;
    }

    public void setStopTime24h(DayTime stopTime24h) {
        this.stopTime24h = stopTime24h;
    }

    public Date getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(Date timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public RoomBooking getRoomBooking() {
        return roomBooking;
    }

    public void setRoomBooking(RoomBooking roomBooking) {
        this.roomBooking = roomBooking;
    }
}
