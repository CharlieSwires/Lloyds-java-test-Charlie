import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BookingApp {

    public static void main(String args[]) {
        BufferedReader br = null;
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
                    sdt = new DayTime(line.subSequence(0, 4));
                    if (!sdt.isValid()) throw new RuntimeException("invalid DayTime");
                    System.out.println(sdt);
                    edt = new DayTime(line.subSequence(5, 9));
                    if (!edt.isValid()) throw new RuntimeException("invalid DayTime");
                    System.out.println(edt);
                    if (!edt.isGreater(sdt)) throw new RuntimeException("invalid DayTime combination");
                } else if (lineCount > 0) {
                    if ((lineCount - 1) % 2 == 0) {
                        
                    }else {
                        
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
