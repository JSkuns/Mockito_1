import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;

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
    @ValueSource(strings = {"172.1.1.1", "172.2.2.2", "172.3.3.3"})
    void rusTest(String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Location location = Mockito.mock(Location.class);

        MessageSender sut = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(location.getCountry()).thenReturn(Country.RUSSIA);
        Mockito.when(geoService.byIp(startsWith("172."))).thenReturn(location);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String expected = "Добро пожаловать";
        String actual = sut.send(headers);
        Assertions.assertEquals(expected, actual);
        System.out.println(" = Test " + ip + " is OK...");
    }

    @ParameterizedTest
    @MethodSource("source")
    void usaTest(String text, String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Location locationRUS = Mockito.mock(Location.class);
        Location locationUSA = Mockito.mock(Location.class);

        MessageSender sut = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(locationRUS.getCountry()).thenReturn(Country.RUSSIA);
        Mockito.when(geoService.byIp(startsWith("172."))).thenReturn(locationRUS);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Mockito.when(locationUSA.getCountry()).thenReturn(Country.USA);
        Mockito.when(geoService.byIp(startsWith("96."))).thenReturn(locationUSA);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String actual = sut.send(headers);
        Assertions.assertEquals(text, actual);
        System.out.println(" = " + text + " " + ip + " is OK...");
    }
    private static Stream<Arguments> source() {
        String rusText = "Добро пожаловать";
        String engText = "Welcome";
        return Stream.of(Arguments.of(rusText, "172.1.1.1"),
                Arguments.of(rusText, "172.2.2.2"),
                Arguments.of(engText, "96.1.1.1"),
                Arguments.of(engText, "96.1.1.1"));
    }

}
