import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        String  testString = "2016-07-18 10:17:06 EMP001";
        Assertions.assertEquals(true,Booking.isValid(testString.substring(0,19),
                testString.substring(20,26)));
        Booking booking = new Booking(testString.substring(0,19),
                testString.substring(20,26));
        Assertions.assertEquals("2016-07-18",booking.getDateOfBooking().toString());
        Assertions.assertEquals("10:17:06",booking.getTimeOfBooking().toString());
        Assertions.assertEquals("EMP001",booking.getEmployee().toString());
       
    }
    @Test
    void test2() {
        String  testString = "2016-07-1  10:17:06 EMP001";
        Assertions.assertEquals(false,Booking.isValid(testString.substring(0,19),
                testString.substring(20,26)));
           
    }
    @Test
    void test3() {
        String  testString = "2016-07-18 10:17:0  EMP001";
        Assertions.assertEquals(false,Booking.isValid(testString.substring(0,19),
                testString.substring(20,26)));
           
    }
    @Test
    void test4() {
        String  testString = "2016-07-18 10:17:0  EMP00";
        Assertions.assertEquals(false,Booking.isValid(testString.substring(0,19),
                testString.substring(20,25)));
           
    }

}
