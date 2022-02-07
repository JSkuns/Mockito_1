import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @MethodSource("city")
    void byIpCityTest(String ip, String city) {
        assertEquals(ip, city);
        System.out.println("City test " + ip + " is OK...");
    }
    private static Stream<Arguments> city() {
        return Stream.of(Arguments.of("127.0.0.1", GeoServiceImpl.LOCALHOST),
                Arguments.of("172.0.32.11", GeoServiceImpl.MOSCOW_IP),
                Arguments.of("96.44.183.149", GeoServiceImpl.NEW_YORK_IP));
    }

    @ParameterizedTest
    @MethodSource("rus")
    void byIpRusTest(String ip) {
        GeoService sut = new GeoServiceImpl();
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.RUSSIA);
        assertEquals(sut.byIp(ip).getCountry(), location.getCountry());
        System.out.println("RUS test " + ip + " is OK... (RUS)");
    }
    private static Stream<Arguments> rus() {
        return Stream.of(Arguments.of("172.1.1.1"),
                Arguments.of("172.2.2.2"));
    }

    @ParameterizedTest
    @MethodSource("usa")
    void byIpUsaTest(String ip) {
        GeoService sut = new GeoServiceImpl();
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.USA);
        assertEquals(sut.byIp(ip).getCountry(), location.getCountry());
        System.out.println("USA test " + ip + " is OK... (USA)");
    }
    private static Stream<Arguments> usa() {
        return Stream.of(Arguments.of("96.1.1.1"),
                Arguments.of("96.2.2.2"));
    }

    @ParameterizedTest
    @MethodSource("coordinates")
    void byCoordinatesTest(Double x, Double y) {
        GeoService sut = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class,
                () -> sut.byCoordinates(x, y));
        System.out.println("Coordinates test is OK...");
    }
    private static Stream<Arguments> coordinates() {
        return Stream.of(Arguments.of(11.1, 22.22),
                Arguments.of(33.33, 44.4));
    }

}
