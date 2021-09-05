import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTest {
    @ParameterizedTest
    @MethodSource("source")
    public void MessageSenderImplTest(String str){
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        if (str.startsWith("172.")) {
            Mockito.when(geoServiceImpl.byIp(str)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        } else if (str.startsWith("96.")) {
            Mockito.when(geoServiceImpl.byIp(str)).thenReturn(new Location("New York", Country.USA, null,  0));
        } else {
            Mockito.when(geoServiceImpl.byIp(str)).thenReturn(new Location(null, null, null, 0));
        }

        LocalizationServiceImpl localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceImpl.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationServiceImpl.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
        Map<String, String> headers = new HashMap<String, String>();

        String expected = null;

        if (str.startsWith("172.")) {
            expected = "Добро пожаловать";
        } else if (str.startsWith("96.")) {
            expected = "Welcome";
        } else {
            expected = null;
        }

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, str);

        String result = messageSenderImpl.send(headers);

        assertEquals(result, expected);
    }
 private static Stream<String> source() {
        return Stream.of("172.12", "76.14", "172.12.356", "76.14.483");
    }
}
