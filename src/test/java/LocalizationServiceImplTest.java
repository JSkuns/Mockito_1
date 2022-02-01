import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

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

    @ParameterizedTest
    @EnumSource(value = Country.class)
    void localeRusTest(Country country) {

        LocalizationService sut = new LocalizationServiceImpl();

        if (country == Country.RUSSIA) {
            assertEquals(sut.locale(ru.netology.entity.Country.RUSSIA), "Добро пожаловать");
            assertSame(country, Country.RUSSIA);
            System.out.println("Country is RUSSIA");
        }

        if (country != Country.RUSSIA) {
            assertNotEquals(sut.locale(ru.netology.entity.Country.RUSSIA), "Welcome");
            assertNotSame(country, Country.RUSSIA);
            System.out.println("Other country");
        }
    }

}