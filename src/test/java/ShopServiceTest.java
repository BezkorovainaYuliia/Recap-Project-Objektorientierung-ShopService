import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual;
        try {
            actual = shopService.addOrder(productsIds);
        } catch (NoSuchProductException e) {
            throw new RuntimeException(e);
        }

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), Status.PROCESSING, LocalDateTime.now().atZone(ZoneId.systemDefault()));
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN

        //THEN
        assertThrows(NoSuchProductException.class, () -> shopService.addOrder(productsIds));
    }
}
