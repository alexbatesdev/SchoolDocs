import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class PiTest {

    @Test
    void testPiBadInput(){
        Pi.GetDigofPi(-1);
    }

    @BeforeAll
    @AfterAll
    @BeforeEach
    @AfterEach
    @Test
    void testPi(){
        //assertEquals(Pi.GetDigofPi(4), '1');
        //fail();
        //assertEquals();
        //assertNotEquals(); //String.equals
        //assertSame(); ==
        //assertNotSame(); !=

        //assertTrue();
        //assertFalse();
        //assertArrayEquals();
    }
}
