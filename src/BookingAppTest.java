import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingAppTest {
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        try {
            FileWriter fw = new FileWriter(new File("test2.txt"));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("0900 1730"+"\n");
            bw.write("2016-07-18 10:17:06 EMP001"+"\n");
            bw.write("2016-07-21 11:00 2"+"\n");
            bw.write("2016-07-18 12:34:56 EMP001"+"\n");
            bw.write("2016-07-21 09:00 3"+"\n");
            bw.close();
            try {
                String[] args = {"test2.txt"};
                BookingApp.main(args);
                Assertions.assertEquals(false,true);
            } catch (RuntimeException e) {
                Assertions.assertEquals(true,true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void test2() {
        try {
            FileWriter fw = new FileWriter(new File("test2.txt"));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("0900 1730"+"\n");
            bw.write("2016-07-18 10:17:06 EMP001"+"\n");
            bw.write("2016-07-21 11:00 2"+"\n");
            bw.write("2016-07-18 10:17:06 EMP001"+"\n");
            bw.write("2016-07-21 09:00 2"+"\n");
            bw.close();
            try {
                String[] args = { "test2.txt"};
                BookingApp.main(args);
                Assertions.assertEquals(false,true);
            } catch (RuntimeException e) {
                Assertions.assertEquals(true,true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    @Test
    void test3() {
        try {
            FileWriter fw = new FileWriter(new File("test2.txt"));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("0900 1730"+"\n");
            bw.write("2016-07-18 10:17:06 EMP001"+"\n");
            bw.write("2016-07-21 11:00 2"+"\n");
            bw.write("2016-07-18 12:34:56 EMP001"+"\n");
            bw.write("2016-07-21 09:00 2"+"\n");
            bw.write("2016-07-18 09:28:23 EMP003"+"\n");
            bw.write("2016-07-22 14:00 2"+"\n");
            bw.write("2016-07-18 11:23:45 EMP004"+"\n");
            bw.write("2016-07-22 16:00 1"+"\n");
            bw.write("2016-07-15 17:29:12 EMP005"+"\n");
            bw.write("2016-07-21 16:00 1"+"\n");
            bw.close();
            String[] args = { "test2.txt"};
            BookingApp.main(args);
            FileReader fr = new FileReader("testOut.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineCount = 0;
            while ((line = br.readLine())!=null) {
                switch(lineCount) {
                case 0:
                    Assertions.assertEquals("2016-07-21",line);
                    break;
                case 1:
                    Assertions.assertEquals("09:00 11:00 EMP001",line);
                    break;
                case 2:
                    Assertions.assertEquals("11:00 13:00 EMP001",line);
                    break;
                case 3:
                    Assertions.assertEquals("16:00 17:00 EMP005",line);
                    break;
                case 4:
                    Assertions.assertEquals("2016-07-22",line);
                    break;
                case 5:
                    Assertions.assertEquals("14:00 16:00 EMP003",line);
                    break;
                case 6:
                    Assertions.assertEquals("16:00 17:00 EMP004",line);
                    break;
                default:
                    Assertions.assertEquals(false,true);
                }
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
