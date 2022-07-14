import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomBookingTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        Assertions.assertEquals(true,RoomBooking.isValid("2016-07-21 09:00 2"));
        Assertions.assertEquals(false,RoomBooking.isValid("2016-07-21 09:00 9"));
        Assertions.assertEquals(false,RoomBooking.isValid("2016-07-21 39:00 2"));
        Assertions.assertEquals(false,RoomBooking.isValid("2016-07-32 09:00 2"));
    }

}
