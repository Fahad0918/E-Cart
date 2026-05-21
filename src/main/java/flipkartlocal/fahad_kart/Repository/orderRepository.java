package flipkartlocal.fahad_kart.Repository;

import flipkartlocal.fahad_kart.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<Order, Integer> {
}
