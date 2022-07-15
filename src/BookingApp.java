import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Lloyds test input file is test.txt output is System.out
 * @author charl
 *
 */
public class BookingApp {
    static BufferedReader br = null;
    static EmployeeSet es = new EmployeeSet();
    static String currentEmployee = null;
    static BookingMap bookings = new BookingMap();
    static RoomBookingMap roomBookings = new RoomBookingMap();
    static DayTime sdt;
    static DayTime edt;
    static int lineCount;
    static String line;
    private static final int FIRST_LINE = 0;
    private static final int LINE_LENGTH_FIRST_LINE = 9;
    private static final int LINE_LENGTH_SECOND_LINE = 26;
    private static final int LINE_LENGTH_THIRD_LINE = 18;
    private static final int EIGHT_HOUR_DAY = 9;
    private static final int END_T_START = 4;
    private static final int END_T_END = 9;
    private static final int DATE_TIME_LENGTH = 19;
    private static final int EMPLOYEE_START = 20;
    /**
     * Gets the first line assumes will be there
     */
    private static void getWorkDay() {
        sdt = new DayTime(line.subSequence(0, END_T_START));
        if (!sdt.isValid()) throw new RuntimeException("invalid DayTime");
        edt = new DayTime(line.subSequence(END_T_START + 1, END_T_END));
        if (!edt.isValid()) throw new RuntimeException("invalid DayTime");
        if (!edt.isGreater(sdt)) throw new RuntimeException("invalid DayTime combination");

    }
    
    /**
     * gets the bookings from the even lines
     */
    private static void getBooking() {
        if (!Booking.isValid(line.substring(0,DATE_TIME_LENGTH),
                line.substring(EMPLOYEE_START,LINE_LENGTH_SECOND_LINE))) throw new RuntimeException("invalid booking:2016-07-18 10:17:06 EMP001");
        Booking booking = new Booking(line.substring(0,DATE_TIME_LENGTH),
                line.substring(EMPLOYEE_START,LINE_LENGTH_SECOND_LINE));
        currentEmployee = booking.getEmployee();        
        es.getEmployees().add(currentEmployee);
        int before = bookings.getBookings().size();
        bookings.getBookings().put(""+booking.getDateOfBooking()
        +booking.getTimeOfBooking()+currentEmployee,booking);
        int after = bookings.getBookings().size();                            
        if (before == after) {
            throw new RuntimeException("duplicate booking time for same employee:"+""+booking.getDateOfBooking()
            +booking.getTimeOfBooking()+currentEmployee);

        }
    }
    
    /**
     * Gets the room reservation from the odd lines
     */
    private static void getRoomBooking() {
        if (!RoomBooking.isValid(line)) {
            throw new RuntimeException("invalid RoomBooking line:"+line+", example:"+"2016-07-21 09:00 2");
        }
        RoomBooking room = new RoomBooking(line);
        room.setEmployee(currentEmployee);
        int before = roomBookings.getRoom().size();
        for (int i = 0; i < room.getDuration(); i++) {
            LocalTime startOfOneHourBlock = room.getStartTime().plusHours((long)i);
            if (new DayTime(startOfOneHourBlock.getHour() > 9 ?
                    ""+startOfOneHourBlock.getHour()+"00":
                        "0"+startOfOneHourBlock.getHour()+"00").isGreater(edt)) {
                throw new RuntimeException("block requested after hours specified");
            }
            roomBookings.getRoom().put(""+room.getStartDate()
            +startOfOneHourBlock,room);
        }
        int after = roomBookings.getRoom().size();                            
        if ((after - before) != room.getDuration()) {
            throw new RuntimeException("duplicate room reservation time:"+""+room.getStartDate()
            +room.getStartTime()+currentEmployee);

        }        
    }
    
    /**
     * Sorts and prints result
     * @throws IOException 
     */
    private static void printResult() throws IOException {
        FileWriter fw = new FileWriter(new File("testOut.txt"));
        BufferedWriter bw = new BufferedWriter(fw);
        List<String> roomBookingList = new ArrayList<>();
        roomBookingList.addAll(roomBookings.getRoom().keySet());
        roomBookingList.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
            
        });
        LocalDate currentDay = null;
        String currentEmployee2 = null;
        int countDown = 0;
        for (String key : roomBookingList) {
            RoomBooking rb = roomBookings.getRoom().get(key);
            if (currentDay == null || !currentDay.equals(rb.getStartDate())) {
                currentDay = rb.getStartDate();
                bw.write(currentDay+"\n");
            }
            if (countDown != 0) {
                countDown--;
                continue;
            }
            if (currentEmployee2 == null || countDown == 0) {
                currentEmployee2 = rb.getEmployee();
                countDown = rb.getDuration()-1;
                bw.write(rb.getStartTime()+" "+rb.getStartTime().plusHours(rb.getDuration())+" "+rb.getEmployee()+"\n");
            }
        }
        bw.close();
    }

    /**
     * Application main
     * @param args
     */
    public static void main(String args[]) {
        sdt = new DayTime("0900");
        edt = new DayTime("1800");
        lineCount = 0;
        es.setEmployees(new HashSet<String>());
        bookings.setBookings(new HashMap<String,Booking>());
        roomBookings.setRoom(new HashMap<String,RoomBooking>());

        try {
            FileReader fr = new FileReader(args[0]);
            br = new BufferedReader(fr);

            while ((line = br.readLine())!= null) {
                if (lineCount == FIRST_LINE) {
                    if (line.length() == LINE_LENGTH_FIRST_LINE) {
                        getWorkDay();
                    } else {
                        throw new RuntimeException("line wrong length:"+line.length());
                    }
                } else if (lineCount > FIRST_LINE) {
                    if ((lineCount - 1) % 2 == 0) { //even line counting from 1
                        if (line.length() == LINE_LENGTH_SECOND_LINE) {
                            getBooking();
                        } else {
                            throw new RuntimeException("line wrong length:"+line.length()+", row:"+lineCount);
                        }
                    } else { //odd line counting from 1
                        if (line.length() == LINE_LENGTH_THIRD_LINE) {
                            getRoomBooking();
                        } else {
                            throw new RuntimeException("line wrong length:"+line.length()+", row:"+lineCount);   
                        }
                    }
                }
                lineCount++;
            }
            printResult();
 
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();          
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
