//Goes in test file OOPS
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
public class CalcTest {

//    @Test
//    void testGetDoubleBadInput() {
//        Program.getDbl("Test Prompt");
//    }
// I don't know how to auto test these, the tests just sit there hanging because I can't input
//    @Test
//    void testGetIntBadInput() {
//        Program.getDbl("Test Prompt");
//    }

    @Test
    void testAddValues() {
        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                assertEquals((double) i + (double) j,Program.add(i, j));
            }
        }
    }

    @Test
    void testSubValues() {
        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                assertEquals((double) i - (double) j,Program.sub(i, j));
                //System.out.println(Program.sub(i, j)); //This was just because I wanted to see the numbers come out
            }
        }
    }

    @Test
    void testDivValues() {
        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                assertEquals((double) i / (double) j,Program.div(i, j));
                // System.out.println(Program.div(i, j));  //This was just because I wanted to see the numbers come out
            }
        }
    }

    @Test
    void testMultValues() {
        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                assertEquals((double) i * (double) j,Program.mult(i, j));
            }
        }
    }
}
