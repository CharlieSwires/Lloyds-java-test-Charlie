import java.util.Date;
import java.util.Set;

public class RoomBooking {
    private Date startTime;
    private Integer duration;
    private Set<String> employee;
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Set<String> getEmployee() {
        return employee;
    }
    public void setEmployee(Set<String> employee) {
        this.employee = employee;
    }
}
