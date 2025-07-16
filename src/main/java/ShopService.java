import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    //edit + Optional
    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId); //Optional
            if (productToOrder.isEmpty()) { //edit
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder.get()); //edit
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, Status.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getAllOrdersByStatus(Status status) {
        List<Order> ordersByStatus = new ArrayList<>();
        for(Order order : orderRepo.getOrders()){
            if(order.status().equals(status)){
                ordersByStatus.add(order);
            }
        }
        return ordersByStatus;
    }
}
