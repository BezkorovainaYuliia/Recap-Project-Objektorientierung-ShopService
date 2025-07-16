import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
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

        assertEquals(actual.size(), expected.size());
        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.systemDefault());
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Status.PROCESSING, time);
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Status.PROCESSING, time);

        assertEquals(actual.id(), expected.id());
        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.systemDefault());
        OrderMapRepo repo = new OrderMapRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Status.PROCESSING, time);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Status.PROCESSING, time);
        assertEquals(actual.id(), expected.id());
        assertEquals(repo.getOrderById("1").id(), expected.id());
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
