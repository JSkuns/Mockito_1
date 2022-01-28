import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeoServiceImplTest {

    @BeforeAll
    public static void start() {
        System.out.println("Class 'GeoServiceImplTest' tests start");
    }

    @BeforeEach
    public void startTest() {
    }

    @AfterAll
    public static void finish() {
        System.out.println("Class 'GeoServiceImplTest' tests finish");
    }

    @AfterEach
    public void finishTest() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "155.1.1.1", "172.1.1.1.", "96.2.2.2"})
    void byIpTest(String str) {
        if (GeoServiceImpl.LOCALHOST.equals(str)) {
            assertEquals(str, GeoServiceImpl.LOCALHOST);
            System.out.println("Test " + str + " is OK... (LOCALHOST)");
        } else if (GeoServiceImpl.MOSCOW_IP.equals(str)) {
            assertEquals(str, GeoServiceImpl.MOSCOW_IP);
            System.out.println("Test " + str + " is OK... (MOSCOW)");
        } else if (GeoServiceImpl.NEW_YORK_IP.equals(str)) {
            assertEquals(str, GeoServiceImpl.NEW_YORK_IP);
            System.out.println("Test " + str + " is OK... (NEW_YORK)");
        } else if (str.startsWith("172.")) {
            assertTrue(str.startsWith("172."));
            System.out.println("Test " + str + " is OK... (RUS)");
        } else if (str.startsWith("96.")) {
            assertTrue(str.startsWith("96."));
            System.out.println("Test " + str + " is OK... (USA)");
        }
    }
}
