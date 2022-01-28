import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class MessageSenderImplTest {

    @BeforeAll
    public static void start() {
        System.out.println("Class 'MessageSenderImpl' tests start");
    }

    @BeforeEach
    public void startTest() {
    }

    @AfterAll
    public static void finish() {
        System.out.println("Class 'MessageSenderImpl' tests finish");
    }

    @AfterEach
    public void finishTest() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "155.1.1.1", "172.1.1.1.", "127.2.2.2"})
    void rusTest(String str) {

        GeoService sut = new GeoServiceImpl();
        GeoService geoService = Mockito.mock(GeoService.class);
        Location location = Mockito.mock(Location.class);

        // ip 172... - RUSSIA
        Mockito.when(geoService.byIp(str)).thenReturn(location); // делаем заглушку geo...
        Mockito.when(location.getCountry()).thenReturn(Country.RUSSIA); // ...location равной RUSSIA
        Country expected = geoService.byIp(str).getCountry(); // ожидаем, что RUSSIA

        Country actual = null; // если ip входит в перечень, то getCountry()
        if (sut.byIp(str) != null) {
            actual = sut.byIp(str).getCountry();
        }
        
        if (str.startsWith("172.")) { // если ip принадлежит российскому сегменту...
            Assertions.assertEquals(expected, actual);
            System.out.println("Test RUS " + str + " - is OK...");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "155.1.1.1", "172.1.1.1.", "127.2.2.2"})
    void engTest(String str) {

        GeoService sut = new GeoServiceImpl();
        GeoService geoService = Mockito.mock(GeoService.class);
        Location location = Mockito.mock(Location.class);

        // ip 96... - USA
        Mockito.when(geoService.byIp(str)).thenReturn(location); // делаем заглушку geo...
        Mockito.when(location.getCountry()).thenReturn(Country.USA); // ...location равной USA
        Country expected = geoService.byIp(str).getCountry(); // ожидаем, что USA

        Country actual = null; // если ip входит в перечень, то getCountry()
        if (sut.byIp(str) != null) {
            actual = sut.byIp(str).getCountry();
        }

        if (str.startsWith("96.")) { // если ip принадлежит американскому сегменту...
            Assertions.assertEquals(expected, actual);
            System.out.println("Test ENG " + str + " - is OK...");
        }
    }

}
