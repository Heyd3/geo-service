import org.junit.jupiter.api.Test;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceImplTest {
    @Test
    public void localeTest(){
        LocalizationServiceImpl localizationServiceImpl = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String result = localizationServiceImpl.locale(RUSSIA);

        assertEquals(expected, result);
    }
}
