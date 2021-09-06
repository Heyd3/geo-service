import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {

    @Test
    public void byIpTest(){
        GeoServiceImpl geoService = new GeoServiceImpl();
        String ip = "172.0.32.11";
        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        Location result  = geoService.byIp(ip);

        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }
    
    @Test
    public void byCoordinates() {
        double a = 5;
        double b = 5;
        GeoServiceImpl geoService = new GeoServiceImpl();
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(a, b));
    }
}
