import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.systemDefault());

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Status.PROCESSING, time);
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), Status.PROCESSING, time));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.systemDefault());

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Status.PROCESSING, time);
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Status.PROCESSING, time);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.systemDefault());
        OrderListRepo repo = new OrderListRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Status.PROCESSING, time);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Status.PROCESSING, time);
        assertEquals(actual.id(), expected.id());
        assertEquals(repo.getOrderById("1").products().size(), expected.products().size());
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1").products(), expected.products());
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
