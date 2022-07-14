import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;

public class BookingApp {

    public static void main(String args[]) {
        BufferedReader br = null;
        EmployeeSet es = new EmployeeSet();
        es.setEmployees(new HashSet<String>());
        String currentEmployee = null;
        try {
            DayTime sdt = new DayTime("0900");
            DayTime edt = new DayTime("1800");
            System.out.println("in try");
            FileReader fr = new FileReader("test.txt");
            br = new BufferedReader(fr);
            int lineCount = 0;
            String line;
            System.out.println("in loop");

            while ((line = br.readLine())!= null) {
                if (lineCount == 0) {
                    if (line.length() == 9) {
                        sdt = new DayTime(line.subSequence(0, 4));
                        if (!sdt.isValid()) throw new RuntimeException("invalid DayTime");
                        System.out.println(sdt);
                        edt = new DayTime(line.subSequence(5, 9));
                        if (!edt.isValid()) throw new RuntimeException("invalid DayTime");
                        System.out.println(edt);
                        if (!edt.isGreater(sdt)) throw new RuntimeException("invalid DayTime combination");
                    } else {
                        throw new RuntimeException("line wrong length:"+line.length());
                    }
                } else if (lineCount > 0) {
                    if ((lineCount - 1) % 2 == 0) {
                        if (line.length() == 26) {
                            if (!Booking.isValid(line.substring(0,19),
                                    line.substring(20,26))) throw new RuntimeException("invalid booking:2016-07-18 10:17:06 EMP001");
                            Booking booking = new Booking(line.substring(0,19),
                                    line.substring(20,26));
                            System.out.println(booking);
                            currentEmployee = booking.getEmployee();        
                            es.getEmployees().add(currentEmployee);
                        } else {
                            throw new RuntimeException("line wrong length:"+line.length()+", row:"+lineCount);
                        }
                    } else {
                        if (line.length() == 18) {
                            if (!RoomBooking.isValid(line)) {
                                throw new RuntimeException("invalid RoomBooking line:"+line+", example:"+"2016-07-21 09:00 2");
                            }
                            RoomBooking room = new RoomBooking(line);
                            room.setEmployee(currentEmployee);
                            System.out.println(room);
                        } else {
                            throw new RuntimeException("line wrong length:"+line.length()+", row:"+lineCount);   
                        }
                    }
                }
                lineCount++;
            }
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
