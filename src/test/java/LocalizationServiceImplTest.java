import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LocalizationServiceImplTest {

    @BeforeAll
    public static void start() {
        System.out.println("Class 'LocalizationServiceImplTest' tests start");
    }

    @BeforeEach
    public void startTest() {
    }

    @AfterAll
    public static void finish() {
        System.out.println("Class 'LocalizationServiceImplTest' tests finish");
    }

    @AfterEach
    public void finishTest() {
    }

    @Test
    void localeRusTest() {
        LocalizationService sut = new LocalizationServiceImpl();
        Location locationRUS = Mockito.mock(Location.class);
        Mockito.when(locationRUS.getCountry()).thenReturn(Country.RUSSIA);
        assertEquals(sut.locale(locationRUS.getCountry()), "Добро пожаловать");
        System.out.println("Language RUS test is OK... (RUSSIA)");
    }

    @ParameterizedTest
    @MethodSource("country")
    void localeUsaTest(Country country) {
        LocalizationService sut = new LocalizationServiceImpl();
        Location locationUSA = Mockito.mock(Location.class);
        Mockito.when(locationUSA.getCountry()).thenReturn(country);
        assertEquals(sut.locale(locationUSA.getCountry()), "Welcome");
        System.out.println("Language USA test is OK... (" + country + ")");
    }
    private static Stream<Arguments> country() {
        return Stream.of(Arguments.of(Country.USA),
                Arguments.of(Country.BRAZIL),
                Arguments.of(Country.GERMANY));
    }

}