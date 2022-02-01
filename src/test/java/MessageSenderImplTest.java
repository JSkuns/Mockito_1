import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

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
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "155.1.1.1", "172.1.1.1", "127.2.2.2"})
    void rusTest(String str) {

        // создаём объект MessageSender заглушку
        MessageSender messageSender = Mockito.mock(MessageSenderImpl.class);
        Map<String, String> mapExp = new HashMap<>();
        mapExp.put(MessageSenderImpl.IP_ADDRESS_HEADER, str);
        Mockito.when(messageSender.send(mapExp)).thenReturn("Добро пожаловать");

        // определим ожидаемый ответ, равный строке 'Добро пожаловать'
        String expected = messageSender.send(mapExp);

        // actual
        String actual;

        // создадим всё как в классе Main
        Map<String, String> mapAct = new HashMap<>();
        mapAct.put(MessageSenderImpl.IP_ADDRESS_HEADER, str);
        GeoService geo = new GeoServiceImpl();
        LocalizationService loc = new LocalizationServiceImpl();

        // проверяем если ip начинается с '172.'
        if (str.startsWith("172.")) {
            MessageSender sut = new MessageSenderImpl(geo, loc);
            actual = sut.send(mapAct);
            Assertions.assertEquals(expected, actual);
            System.out.println(" -> Test RUS " + str + " - is OK...");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.0.1", "172.0.32.11", "96.44.183.149", "155.1.1.1", "172.1.1.1", "127.2.2.2"})
    void engTest(String str) {

        // создаём объект MessageSender заглушку
        MessageSender messageSender = Mockito.mock(MessageSenderImpl.class);
        Map<String, String> mapExp = new HashMap<>();
        mapExp.put(MessageSenderImpl.IP_ADDRESS_HEADER, str);
        Mockito.when(messageSender.send(mapExp)).thenReturn("Welcome");

        // определим ожидаемый ответ, равный строке 'Welcome'
        String expected = messageSender.send(mapExp);

        // actual
        String actual;

        // создадим всё как в классе Main
        Map<String, String> mapAct = new HashMap<>();
        mapAct.put(MessageSenderImpl.IP_ADDRESS_HEADER, str);
        GeoService geo = new GeoServiceImpl();
        LocalizationService loc = new LocalizationServiceImpl();

        // проверяем если ip начинается с '96.'
        if (str.startsWith("96.")) {
            MessageSender sut = new MessageSenderImpl(geo, loc);
            actual = sut.send(mapAct);
            Assertions.assertEquals(expected, actual);
            System.out.println(" -> Test ENG " + str + " - is OK...");
        }
    }
}
