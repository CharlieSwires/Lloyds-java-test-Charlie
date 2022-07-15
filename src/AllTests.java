import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllTests {
    BookingAppTest bat;
    BookingTest bt;
    DayTimeTest dtt;
    RoomBookingTest rbt;
    
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        bat = new BookingAppTest();
        bat.test();
        bat.test2();
        bat.test3();
    }
    @Test
    void test2() {
        bt = new BookingTest();
        bt.test();
        bt.test2();
        bt.test3();
        bt.test4();
    }
    @Test
    void test3() {
        dtt = new DayTimeTest();
        dtt.test();
        dtt.test2();
    }
    @Test
    void test4() {
        rbt = new RoomBookingTest();
        rbt.test();
    }

}
