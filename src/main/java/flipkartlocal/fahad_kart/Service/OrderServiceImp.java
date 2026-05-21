package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Order;
import flipkartlocal.fahad_kart.Repository.orderRepository;
import flipkartlocal.fahad_kart.Service.orderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements orderService {


    private  orderRepository orderRepo;

    public OrderServiceImp(orderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

}
