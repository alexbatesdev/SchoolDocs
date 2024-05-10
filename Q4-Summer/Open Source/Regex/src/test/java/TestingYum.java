
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestingYum {


    @Test
    void numTestValid() {
        Person p = new Person();
        assertTrue(p.setPhoneNum("385-123-9876"));
    }

    @Test
    void numTestInvalid() {
        Person p = new Person();
        assertFalse(p.setPhoneNum("85-13323-9876"));
    }
}
