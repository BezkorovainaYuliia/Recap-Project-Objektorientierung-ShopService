
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@ToString
@RequiredArgsConstructor
public class ShopService {
    @NonNull
    private final ProductRepo productRepo;
    @NonNull
    private final OrderRepo orderRepo;
    @NonNull
    private final IdService idService;

    public ShopService() {
        productRepo = new ProductRepo();
        orderRepo = new OrderMapRepo();
        idService = new IdService();
    }
    //edit + Optional
    //edit Exeption
    public Order addOrder(List<String> productIds) throws NoSuchProductException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId); //Optional
            if (productToOrder.isEmpty()) { //edit
                //add throw NoSuchException
                throw new NoSuchProductException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get()); //edit
        }

        Order newOrder = new Order(idService.generateID(), products, Status.PROCESSING, LocalDateTime.now().atZone(ZoneId.systemDefault()));

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

    public void updateOrder(String id, Status status) {
        Order updatOrder = orderRepo.getOrderById(id);
        updatOrder = updatOrder.withStatus(status);
        orderRepo.updateOrder(updatOrder);
    }

    public Map<Status,Order> getOldestOrderPerStatus(){
        return orderRepo.getOrders().stream()
                .collect(Collectors.toMap(
                        Order::status,
                        order -> order,
                        (o1, o2) -> o1.timestamp().isBefore(o2.timestamp()) ? o1 : o2
                ));
    }
}
