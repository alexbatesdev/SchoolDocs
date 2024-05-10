import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;

public class StonkTesting {

    @Test
    void TestHTML() {
        for (int i = 1; i <= 300; i++) {
            String path = String.format("data/%d.html", i);
            File f = new File(path);
            assertTrue(f.exists());
        }

    }
}
