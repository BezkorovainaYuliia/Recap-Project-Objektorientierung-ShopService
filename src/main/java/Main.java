import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        OrderListRepo orderListRepo = new OrderListRepo();
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        OrderMapRepo orderMapRepo2 = new OrderMapRepo();

        IdService idService = new IdService();

        // create products
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Banan"));
        productRepo.addProduct(new Product("3", "Gurken"));
        productRepo.addProduct(new Product("4", "Zwibel"));
        productRepo.addProduct(new Product("5", "Milch"));
        productRepo.addProduct(new Product("6", "Käse"));
        productRepo.addProduct(new Product("7", "Wasser"));
        productRepo.addProduct(new Product("8", "Cola"));
        productRepo.addProduct(new Product("9", "Orange"));
        productRepo.addProduct(new Product("10", "Graipfruit"));

        //add Orders
        List<String> orderProductsID = List.of("2", "3", "4", "6", "7", "11");
        List<String> orderProductsID2 = List.of("1", "2", "9", "8", "10");
        List<String> orderProductsID3 = List.of("6", "7", "8", "9", "10");

        ShopService shopService = new ShopService(productRepo, orderListRepo, idService);

        try {
            Order order = shopService.addOrder(orderProductsID);
        } catch (NoSuchProductException e) {
            System.out.println(e.toString());
            System.out.println("Order is canceled");
        }


        try {
            Order order2 = shopService.addOrder(orderProductsID2);
        } catch (NoSuchProductException e) {
            System.out.println(e.toString());
            System.out.println("Order is canceled");
        }

        try {
            Order order3 = shopService.addOrder(orderProductsID2);
        } catch (NoSuchProductException e) {
            System.out.println(e.toString());
            System.out.println("Order is canceled");
        }

        Map<Status, Order> getMap = shopService.getOldestOrderPerStatus();
        getMap.forEach((status, order) -> {
            System.out.println(status);
            System.out.println(order);
        });

    }
}
