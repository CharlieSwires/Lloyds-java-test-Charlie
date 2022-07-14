import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTimeTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        DayTime dt = new  DayTime ("0000");
        Assertions.assertEquals(true,dt.isValid());
        dt = new  DayTime ("000");
        Assertions.assertEquals(false,dt.isValid());
        dt = new  DayTime ("2359");
        Assertions.assertEquals(true,dt.isValid());
        dt = new  DayTime ("2459");
        Assertions.assertEquals(false,dt.isValid());
        dt = new  DayTime ("00000");
        Assertions.assertEquals(false,dt.isValid());
        dt = new  DayTime ("3359");
        Assertions.assertEquals(false,dt.isValid());
        dt = new  DayTime ("2369");
        Assertions.assertEquals(false,dt.isValid());
    }
    @Test
    void test2() {
        DayTime dt = new  DayTime ("0000");
        Assertions.assertEquals(0,dt.timeInMinsMethod(dt.getTimehhmm()));
        dt = new  DayTime ("000");
        try {
        Assertions.assertEquals(0,dt.timeInMinsMethod(dt.getTimehhmm()));
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(true,true);
        }
        dt = new  DayTime ("2359");
        Assertions.assertEquals(1439,dt.timeInMinsMethod(dt.getTimehhmm()));
    }

}
